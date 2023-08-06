let userId = window.userId; // Global variable to hold the user's ID for calling in other method.
let userLocation = window.userLocation; // Global variable to hold the user's location for calling in other method.

document.addEventListener('DOMContentLoaded', (event) => {
    // Fetch the profile now that the user is logged in
    fetch('/auth/getProfile', {
        method: 'GET',
        credentials: 'include' // Include the HTTPOnly cookie in the request
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);

        // Store the userId and location in the global variable
        userId = data.userId;
        userLocation = data.location;

        // Insert user data into HTML
        document.getElementById('username').textContent = data.username;
        // Check if profilePic is null, if so, use a generic picture
        if (data.profilePic == null) {
            document.getElementById('profilePic').src = '/images/charlesdeluvio-sRBUzcxqt6c-unsplash.jpg';
        } else {
            document.getElementById('profilePic').src = `data:image/png;base64,${data.profilePic}`;
        }
        // Check if profileCaption is null, if so, display default message
        if (data.profileCaption == null) {
            document.getElementById('profileCaption').textContent = 'No caption set';
        } else {
            document.getElementById('profileCaption').textContent = '"' + data.profileCaption + '"';
        }
        document.getElementById('locationText').textContent = data.location;

        // todo must add calendars and forum here
    })
    .catch((error) => {
       console.error('Error:', error);
    });
});