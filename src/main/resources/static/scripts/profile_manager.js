document.addEventListener('DOMContentLoaded', (event) => {
    userId = localStorage.getItem('userId');
    console.log("Retrieved userId:", userId);


    // Monitor for changes to the user's profile and display dynamically
    document.getElementById('profilePicInput').addEventListener('change', updateProfilePic);
    document.getElementById('profileCaptionInput').addEventListener('change', updateProfileCaption);
    document.getElementById('locationInput').addEventListener('change', updateLocation);

    async function updateProfilePic(e) {
        let file = e.target.files[0];
        let formData = new FormData();
        formData.append('file', file);

        try {
            let response = await fetch(`/profile/uploadProfilePic`, {
                method: 'POST',
                credentials: 'include',
                body: formData
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            } else {
                let result = await response.text();
                document.getElementById('profilePic').src = `data:image/png;base64,${result}`;
            }
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
                body: JSON.stringify({ newProfileCaption: newCaption }),
                credentials: 'include'
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            } else {
                let result = await response.text();
                document.getElementById('profileCaption').textContent = newCaption;
            }
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
                body: JSON.stringify({ newLocation : newLocation }),
                credentials: 'include'
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            } else {
                let result = await response.text();
                document.getElementById('locationText').textContent = newLocation;
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }
    // Toggle the profile pic input
    document.getElementById('changePic').addEventListener('click', function (e) {
        e.preventDefault();
        let profilePicInput = document.getElementById('profilePicInput');
        if (profilePicInput.style.display === 'none' || profilePicInput.style.display === '') {
            profilePicInput.style.display = 'block';
        } else {
            profilePicInput.style.display = 'none';
        }
    });
    // Handle the profile caption submission
    document.getElementById('submitPic').addEventListener('click', async function(e) {
        e.preventDefault();
        let fileInput = document.querySelector('#profilePicInput input[type=file]');
        let file = fileInput.files[0];
        let picResult = document.getElementById('picResult');
        // Empty submission
        if (!file) {
            picResult.textContent = 'Please select a file to upload.';
            picResult.style.display = 'block';
            picResult.style.color = 'red';
            return;
        }

        let formData = new FormData();
        formData.append('file', file);

        try {
            let response = await fetch('/profile/uploadProfilePic', {
                method: 'POST',
                credentials: 'include',
                body: formData
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            } else {
                let result = await response.text();
                picResult.textContent = 'Successfully uploaded the picture.';
                picResult.style.display = 'block';
                picResult.style.color = 'green';
                document.getElementById('profilePicInput').style.display = 'none';
                document.getElementById('changePic').style.display = 'none';
            }
        } catch(error) {
            picResult.textContent = 'Error uploading picture: ' + error.message;
            picResult.style.display = 'block';
            picResult.style.color = 'red';
        }
    });
    // Toggle the caption anchor
    document.getElementById('changeCaption').addEventListener('click', function (e) {
        e.preventDefault();
        let profileCaptionInput = document.getElementById('profileCaptionInput');
        if (profileCaptionInput.style.display === 'none' || profileCaptionInput.style.display === '') {
            profileCaptionInput.style.display = 'block';
        } else {
            profileCaptionInput.style.display = 'none';
        }
    });
    // Handle the location submission
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
            console.log(userId);
            let response = await fetch(`/profile/${userId}/updateProfileCaption`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ newProfileCaption : newCaption }),
                credentials: 'include'
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            } else {
                let result = await response.text();
                captionResult.textContent = result;
                captionResult.style.color = 'green';
                captionResult.style.display = 'block';
                document.getElementById('profileCaptionInput').style.display = 'none';
                document.getElementById('changeCaption').style.display = 'none';
            }
        } catch (error) {
            captionResult.textContent = 'Error updating caption: ' + error.message;
            captionResult.style.display = 'block';
            captionResult.style.color = 'red';
        }
    });
    // Toggle the location anchor
    document.getElementById('changeLocation').addEventListener('click', function (e) {
        e.preventDefault();
        let locationInput = document.getElementById('locationInput');
        if (locationInput.style.display === 'none' || locationInput.style.display === '') {
            locationInput.style.display = 'block';
        } else {
            locationInput.style.display = 'none';
        }
    });

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

        console.log('Within update location function userId: ' + userId);
        try {
            let response = await fetch(`/profile/${userId}/updateLocation`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ newLocation : newLocation }),
                credentials: 'include'
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            } else {
            console.log(response);
                let result = await response.text();
                locationResult.textContent = result;
                locationResult.style.display = 'block';
                locationResult.style.display = 'green';
                document.getElementById('locationInput').style.display = 'none';
                document.getElementById('changeLocation').style.display = 'none';
            }
        } catch (error) {
            locationResult.textContent = 'Error updating location: ' + error.message;
            locationResult.style.display = 'block';
            locationResult.style.color = 'red';
        }
    });

});
