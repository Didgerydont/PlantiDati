document.addEventListener('DOMContentLoaded', (event) => {
    // global variable set in get_profile.js
    let userId = window.userId;

    // Add change event listeners so that the user can view their changes on the profile page
    document.getElementById('profilePicInput').addEventListener('change', updateProfilePic);
    document.getElementById('profileCaptionInput').addEventListener('change', updateProfileCaption);
    document.getElementById('locationInput').addEventListener('change', updateLocation);

    // Functions for updating the user's profile information when it has been changed
    async function updateProfilePic(e) {
        let file = e.target.files[0];
        let formData = new FormData();
        formData.append('file', file);
        formData.append('userId', userId);

        try {
            let response = await fetch(`/profile/uploadProfilePic`, {
                method: 'POST',
                body: formData
            });
            let result = await response.json();
            document.getElementById('profilePic').src = `data:image/png;base64,${result.profilePic}`;
        } catch (error) {
            console.error('Error:', error);
        }
    }

    async function updateProfileCaption(e) {
        let newCaption = e.target.value;

        try {
            let response = await fetch(`/profile/${userId}/updateProfileCaption`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ caption: newCaption })
            });
            let result = await response.json();
            document.getElementById('profileCaption').textContent = result.caption;
        } catch (error) {
            console.error('Error:', error);
        }
    }

    async function updateLocation(e) {
        let newLocation = e.target.value;

        try {
            let response = await fetch(`/profile/${userId}/updateLocation`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ location: newLocation })
            });
            let result = await response.json();
            document.getElementById('locationText').textContent = result.location;
        } catch (error) {
            console.error('Error:', error);
        }
    }

    // Show the profile picture input when 'Change profile picture?' is clicked
    document.getElementById('changePic').addEventListener('click', function (e) {
        e.preventDefault();
        let profilePicInput = document.getElementById('profilePicInput');
        if (profilePicInput.style.display === 'none' || profilePicInput.style.display === '') {
            profilePicInput.style.display = 'block';
        } else {
            profilePicInput.style.display = 'none';
        }
    });

    // Handle profile picture file upload when 'Update Picture' is clicked
    document.getElementById('submitPic').addEventListener('click', async function (e) {
        e.preventDefault();
        let file = document.querySelector('#profilePicInput input[type=file]').files[0];
        let picResult = document.getElementById('picResult');
        if (!file) {
            picResult.textContent = 'Please select a file to upload.';
            picResult.style.display = 'block';
            picResult.style.color ='red';
            return;
        }
        let formData = new FormData();
        formData.append('file', file);
        formData.append('username', 'username');

        try {
            let response = await fetch('/profile/uploadProfilePic', {
                method: 'POST',
                body: formData
            });
            let result = await response.text();
            console.log(result);
            picResult.textContent = result;
            picResult.style.display = 'block';
            document.getElementById('profilePicInput').style.display = 'none';
        } catch (error) {
            console.log('Error:', error);
            picResult.textContent = 'Error uploading picture: ' + error;
            picResult.style.display = 'block';
            picResult.style.color ='red';
        }
    });

    // Show the profile caption input when 'Change profile caption?' is clicked
    document.getElementById('changeCaption').addEventListener('click', function (e) {
        e.preventDefault();
        let profileCaptionInput = document.getElementById('profileCaptionInput');
        if (profileCaptionInput.style.display === 'none' || profileCaptionInput.style.display === '') {
            profileCaptionInput.style.display = 'block';
        } else {
            profileCaptionInput.style.display = 'none';
        }
    });

    // Handle profile caption upload when 'Update Caption' is clicked
    document.getElementById('submitCaption').addEventListener('click', async function (e) {
        e.preventDefault();
        let newCaption = document.querySelector('#profileCaptionInput textarea').value;
        let captionResult = document.getElementById('captionResult');
        if (!newCaption.trim()) {
            captionResult.textContent = 'Please enter a caption.';
            captionResult.style.display = 'block';
            captionResult.style.color = 'red';
            return;
        }
        try {
            let response = await fetch(`/profile/${userId}/updateProfileCaption`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ caption: newCaption })
            });
            let result = await response.text();
            console.log(result);
            captionResult.textContent = result;
            captionResult.style.display = 'block';
            document.getElementById('profileCaptionInput').style.display = 'none';
        } catch (error) {
            console.log('Error:', error);
            captionResult.textContent = 'Error updating caption: ' + error;
            captionResult.style.display = 'block';
            captionResult.style.color = 'red';
        }
    });

    // Show the profile location input when 'Change profile location?' is clicked
    document.getElementById('changeLocation').addEventListener('click', function (e) {
        e.preventDefault();
        let locationInput = document.getElementById('locationInput');
        if (locationInput.style.display === 'none' || locationInput.style.display === '') {
            locationInput.style.display = 'block';
        } else {
            locationInput.style.display = 'none';
        }
    });

    // Handle profile location file upload when 'Update Location' is clicked
    document.getElementById('submitLocation').addEventListener('click', async function (e) {
        e.preventDefault();
        let newLocation = document.querySelector('#locationInput input[type=text]').value;
        let locationResult = document.getElementById('locationResult');
        if (!newLocation.trim()) {
            locationResult.textContent = 'Please enter a location.';
            locationResult.style.display = 'block';
            locationResult.style.color = 'red';
            return;
        }
        try {
            let response = await fetch(`/profile/${userId}/updateLocation`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ location: newLocation })
            });
            let result = await response.text();
            console.log(result);
            locationResult.textContent = result;
            locationResult.style.display = 'block';
            document.getElementById('locationInput').style.display = 'none';
        } catch (error) {
            console.log('Error:', error);
            locationResult.textContent = 'Error updating location: ' + error;
            locationResult.style.display = 'block';
            locationResult.style.color = 'red';
        }
    });
});
