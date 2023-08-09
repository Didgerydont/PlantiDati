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


        plantSelect.addEventListener('change', function(event) {
            let selectedPlantId = this.value;  // 'this' refers to the select element
            localStorage.setItem('plantId', selectedPlantId);
        });
    });

// Event listener for when the combined form is submitted
document.getElementById('combinedForm').addEventListener('submit', function(event) {
    event.preventDefault();

    let title = document.getElementById('title').value;
    let userId = localStorage.getItem('userId');
    let location = localStorage.getItem('userLocation');
    let plantId = document.getElementById('plantId').value;

    // Get selected variety
    let selectedVarietyRadio = document.querySelector('input[name="variety"]:checked');
    let selectedVarietyId = selectedVarietyRadio ? selectedVarietyRadio.value : null;

    // If the new variety checkbox is selected, then create a new variety
    if (document.querySelector("#newVarietyCheckbox").checked) {
        let newVarietyName = document.querySelector("#newVarietyName").value;
        let newVarietyDescription = document.querySelector("#newVarietyDescription").value;

        createNewVariety(plantId, newVarietyName, newVarietyDescription)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                console.error('Error creating new variety.');
                return null;
            }
        })
        .then(varietyId => {
            if(varietyId) {
                createCalendar(title, userId, location, varietyId);
            }
        });

    } else {
        createCalendar(title, userId, location, selectedVarietyId);
    }
});

function createCalendar(title, userId, location, varietyId) {
    fetch('/calendar/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            title: title,
            userId: userId,
            location: location,
            varietyId: varietyId
        })
    })
    .then(response => {
        if (response.ok) {
            console.log('Calendar created successfully!');

        } else {
            console.error('Error creating calendar: ' + response.statusText);
        }
    });
}

document.addEventListener('DOMContentLoaded', () => {
    let plantId = localStorage.getItem('plantId');
    fetchAndPopulateVarieties(plantId);
});

document.querySelector("#newVarietyCheckbox").addEventListener('change', (event) => {
    let newVarietyDiv = document.querySelector("#newVarietyDiv");
    if (event.target.checked) {
        newVarietyDiv.style.display = "block";
        let radioButtons = document.querySelectorAll('input[name="variety"]');
        radioButtons.forEach(rb => rb.checked = false);
    } else {
        newVarietyDiv.style.display = "none";
    }
});

document.querySelector("#varietyRadio").addEventListener('change', (event) => {
    if (event.target.type === "radio") {
        document.querySelector("#newVarietyCheckbox").checked = false;
        document.querySelector("#newVarietyDiv").style.display = "none";
    }
});

function fetchAndPopulateVarieties(plantId) {
    fetch(`/variety/plant/${plantId}`, { credentials: 'include' })
    .then(response => {
        console.log("Response Status: ", response.status);
        if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        return response.json();
    })
    .then(varieties => {
        let varietyRadioDiv = document.querySelector("#varietyRadio");
        varieties.forEach(variety => {
            let containerDiv = document.createElement('div');
            containerDiv.className = 'variety-container';

            let radioInput = document.createElement('input');
            radioInput.type = 'radio';
            radioInput.name = 'variety';
            radioInput.value = variety.varietyId;
            radioInput.id = `variety-${variety.varietyId}`;

            let label = document.createElement('label');
            label.textContent = variety.varietyName;
            label.htmlFor = radioInput.id;

            containerDiv.appendChild(radioInput);
            containerDiv.appendChild(label);
            varietyRadioDiv.appendChild(containerDiv);
        });
    })
    .catch(error => {
        console.error("There was a problem with the fetch operation:", error.message)
    });

}

function createNewVariety(plantId, newVarietyName, newVarietyDescription) {
    return fetch(`/variety/createVariety`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        credentials: 'include',
        body: JSON.stringify({
            variety: {
                varietyName: newVarietyName,
                varietyDescription: newVarietyDescription
            },
            plantId: plantId
        })
    });
}
