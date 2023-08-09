// Fetch all plants and populate the select dropdown
fetch('/plant/getAll')
    .then(response => response.json())
    .then(data => {
        let plantSelect = document.getElementById('plantId');
        data.forEach(plant => {
            let option = document.createElement('option');
            option.value = plant.plantId;
            option.text = plant.plantName;
            plantSelect.add(option);
        });
    });

// Event listener for when the form is submitted
document.getElementById('calendarForm').addEventListener('submit', function(event) {
    event.preventDefault();  // Prevent the form from submitting normally

    let title = document.getElementById('title').value;
    let userId = localStorage.getItem('userId');
    console.log(userId);
    let location = localStorage.getItem('userLocation');
    console.log(location);
    let plantId = document.getElementById('plantId').value;


    // Create calendar with the information from the form
    fetch('/calendar/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            title: title,
            userId: userId,
            location: window.location.href,
            plantId: plantId
        })
    })
    .then(response => {
        if (response.ok) {
            console.log('Calendar created successfully!');
            localStorage.setItem('plantId', plantId);
            window.location.href = "/firstCalendarEntry";
        } else {
            console.log('Error creating calendar' + response.statusText);

        }
    });
});
