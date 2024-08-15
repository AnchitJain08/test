// In a real application, this data would come from the server
let students = [
    {studentNo: '001', studentName: 'John Doe', email: 'john@example.com', phone: '123-456-7890', course: 'Java Programming'},
    {studentNo: '002', studentName: 'Jane Smith', email: 'jane@example.com', phone: '987-654-3210', course: 'Web Development'},
    {studentNo: '003', studentName: 'Alice Johnson', email: 'alice@example.com', phone: '555-555-5555', course: 'Database Management'},
    // Add more sample students as needed
];

function searchStudents() {
    let searchTerm = document.getElementById('searchTerm').value.toLowerCase();
    
    // Filter students based on the search term
    let results = students.filter(student => 
        student.studentNo.toLowerCase().includes(searchTerm) ||
        student.studentName.toLowerCase().includes(searchTerm) ||
        student.email.toLowerCase().includes(searchTerm) ||
        student.phone.toLowerCase().includes(searchTerm) ||
        student.course.toLowerCase().includes(searchTerm)
    );

    displaySearchResults(results);

    // If using a backend API, you would use this instead:
    /*
    fetch('/api/students/search?term=' + encodeURIComponent(searchTerm))
        .then(response => response.json())
        .then(results => displaySearchResults(results))
        .catch(error => {
            console.error('Error:', error);
            displaySearchResults([]);
        });
    */
}

function displaySearchResults(results) {
    let resultsDiv = document.getElementById('searchResults');
    if (results.length === 0) {
        resultsDiv.innerHTML = '<p class="text-red-500">No results found.</p>';
    } else {
        resultsDiv.innerHTML = `
            <table class="w-full border-collapse border">
                <thead>
                    <tr>
                        <th class="border p-2">Student No</th>
                        <th class="border p-2">Name</th>
                        <th class="border p-2">Email</th>
                        <th class="border p-2">Phone</th>
                        <th class="border p-2">Course</th>
                    </tr>
                </thead>
                <tbody>
                    ${results.map(student => `
                        <tr>
                            <td class="border p-2">${student.studentNo}</td>
                            <td class="border p-2">${student.studentName}</td>
                            <td class="border p-2">${student.email}</td>
                            <td class="border p-2">${student.phone}</td>
                            <td class="border p-2">${student.course}</td>
                        </tr>
                    `).join('')}
                </tbody>
            </table>
        `;
    }
    resultsDiv.innerHTML += `
        <button onclick="clearSearchResults()" class="mt-4 bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded">
            Clear Results
        </button>`;
}

function clearSearchResults() {
    document.getElementById('searchTerm').value = '';
    document.getElementById('searchResults').innerHTML = '';
}

// Event listener for real-time search
document.getElementById('searchTerm').addEventListener('input', function() {
    if (this.value.length >= 2) {  // Only search if 2 or more characters are entered
        searchStudents();
    } else if (this.value.length === 0) {
        clearSearchResults();
    }
});
