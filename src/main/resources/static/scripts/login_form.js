document.addEventListener('DOMContentLoaded', (event) => {
    document.getElementById("loginForm").addEventListener("submit", function(event){
        event.preventDefault()

        let username = document.getElementById('username').value;
        let password = document.getElementById('password').value;

        console.log('Username: ', username);
        console.log('Password: ', password);

        fetch('/auth/authenticate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
        .then(response => {
            // Check if response is ok
            if (!response.ok) {
                throw new Error('Incorrect username/password');
            }
            console.log('Response: ', response);
            return response.json();
        })
        .then(function(data) {
            console.log('Success:', data);
            // Redirect to /profile
            window.location.href = "/profile";
        })
        .catch((error) => {
           console.error('Error:', error);
           // Display error message
           let errorMessage = document.getElementById('errorMessage');
           errorMessage.textContent = error.message;
           errorMessage.style.display = 'block';
        });
    });
});
