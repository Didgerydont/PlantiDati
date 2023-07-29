// Registration form management
document.getElementById('registerForm').addEventListener('submit', function(event) {
    // Prevent the form from being submitted normally
    event.preventDefault();

    // Create a new user object based on the form fields
    var newUser = {
        username: document.getElementById('username').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        location: document.getElementById('location').value,
        profileCaption: document.getElementById('profileCaption').value
    };

    // Send the user data to the server
    fetch('/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newUser)
    })
    .then(function(response) {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error: ' + response.statusText);
        }
    })
    .then(function(data) {
        console.log('Success:', data);
        // Redirect the user to the registration success page
        window.location.href = "/registrationsuccess";
    })
    .catch(function(error) {
        // Log the error for debugging
        console.error('Error:', error);

        // Log the error for debugging
        console.error('Error:', error);

        // Show a generic error message to the user
        document.getElementById('usernameError').textContent = 'An error occurred while checking the username. Please try again later.';
        document.getElementById('usernameError').style.display = 'block';
    });
});

// Check if user name has been taken
var usernameCheckTimeout;

document.getElementById('username').addEventListener('input', function(event) {
    clearTimeout(usernameCheckTimeout);  // Clear the timeout if it's been set

    var username = event.target.value;

    // Set a new timeout to perform the username check after the user stops typing for 500 ms
    usernameCheckTimeout = setTimeout(function() {
        fetch('/auth/isusernametaken?username=' + username)
            .then(function(response) {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Error: ' + response.statusText);
                }
            })
            .then(function(data) {
                if (data) {
                    // Show an error message
                    document.getElementById('usernameError').style.display = 'block';
                } else {
                    // Hide the error message
                    document.getElementById('usernameError').style.display = 'none';
                }
            })
            .catch(function(error) {
                // Log the error for debugging
                console.error('Error:', error);

                // Show a generic error message to the user
                document.getElementById('usernameError').textContent = 'An error occurred while checking the username. Please try again later.';
                document.getElementById('usernameError').style.display = 'block';
            });
    }, 500);
});

// Check that passwords match
function validatePasswords() {
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirmPassword').value;

    if (password !== confirmPassword) {
        alert('Passwords do not match.');
        return false;
    }

    return true;
}

