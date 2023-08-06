document.querySelector("#firstEntryForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    let plantId = localStorage.getItem('plantId');

    let newVarietyName = document.querySelector("#newVarietyName").value;
    let newVarietyDescription = document.querySelector("#newVarietyDescription").value;

    let selectedVarietyRadio = document.querySelector('input[name="variety"]:checked');
    let selectedVarietyId = selectedVarietyRadio ? selectedVarietyRadio.value : null;

    let informationBox = document.querySelector("#informationBox");
    informationBox.textContent = "";  // Clear previous message

    // Check if new variety is being created
    if (document.querySelector("#newVarietyCheckbox").checked) {
        if (newVarietyName == "" || newVarietyDescription == "") {
            informationBox.textContent = "New Variety Name and Description are required.";
            return;
        }
        let response = await createNewVariety(plantId, newVarietyName, newVarietyDescription);
        if (!response.ok) {
            informationBox.textContent = "Error creating new variety. Please try again.";
            return;
        }
        selectedVarietyId = await response.json();
    }
    else if (selectedVarietyId == null) {
        informationBox.textContent = "Please select or create a new variety.";
        return;
    }


});

function createNewVariety(plantId, newVarietyName, newVarietyDescription) {
    return fetch(`/api/plants/${plantId}/varieties`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        }
        credentials: 'include',
        body: JSON.stringify({
            name: newVarietyName,
            description: newVarietyDescription,
            plantId: plantId
        })
    });
}