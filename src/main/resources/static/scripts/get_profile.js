document.addEventListener('DOMContentLoaded', (event) => {
    // Fetch the profile now that the user is logged in
    fetch('/auth/getProfile', {
        method: 'GET',
        credentials: 'include' // Include the HTTPOnly cookie in the request
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);

        // Save the userId and location in localStorage
        localStorage.setItem('userId', data.userId);
        console.log(localStorage.getItem('userId'));
        localStorage.setItem('userLocation', data.location);

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


        // Display calendars
        fetchAllCalendars(data.userId);
    })
    .catch((error) => {
       console.error('Error:', error);
    });
});

// Fetch all calendars for the user
function fetchAllCalendars(userId) {
    fetch(`/calendar/getUserCalendars/${userId}`, {
        method: 'GET',
        credentials: 'include'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        // Call the displayCalendar function for each calendar in the data
        data.forEach(calendar => {
        console.log(calendar);
        displayCalendar(calendar);
            });
    })
    .catch(error => {
       console.error('Error fetching calendars:', error);
    });
}

function displayCalendar(calendar) {
    // Get the container where you'd like to display the calendars
    const container = document.getElementById('calendarsContainer');

    // Create a new list-group div for each calendar
    const listGroup = document.createElement('div');
    listGroup.classList.add('list-group', 'mb-3');

    // Add the title
    const title = document.createElement('a');
    title.innerHTML = `<strong>Title:</strong> ${calendar.title ?? "Unknown Title"}`;
    title.href = '#';  // todo Will need to add the Calendar Specific HTML page here
    title.classList.add('list-group-item', 'list-group-item-action', 'active');
    listGroup.appendChild(title);

    // Add the combined plant name and variety name
    const plantAndVariety = document.createElement('li');
    const plantNameText = `<strong>Plant Name:</strong> ${calendar.plantName ?? "Unknown Plant"}`;
    const varietyNameText = `<strong>Variety Name:</strong> ${calendar.varietyName ?? "Unknown Variety"}`;
    plantAndVariety.innerHTML = `${plantNameText}, ${varietyNameText}`;
    plantAndVariety.classList.add('list-group-item');
    listGroup.appendChild(plantAndVariety);

    // Add the variety description
    const variety = document.createElement('li');
    variety.innerHTML = `<strong>Variety:</strong> ${calendar.varietyDescription ?? "Unknown Variety"}`;
    variety.classList.add('list-group-item');
    listGroup.appendChild(variety);

    // Add the location
    const location = document.createElement('li');
    location.innerHTML = `<strong>Location:</strong> ${calendar.location ?? "Unknown Location"}`;
    location.classList.add('list-group-item');
    listGroup.appendChild(location);

    container.appendChild(listGroup);
}
