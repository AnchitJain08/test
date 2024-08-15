function searchStudents() {
    let searchTerm = document.getElementById('searchTerm').value.toLowerCase();
    
    let results = students.filter(student => 
        student.studentNo.toLowerCase().includes(searchTerm) ||
        student.studentName.toLowerCase().includes(searchTerm) ||
        student.email.toLowerCase().includes(searchTerm) ||
        student.phone.toLowerCase().includes(searchTerm) ||
        student.course.toLowerCase().includes(searchTerm)
    );

    displaySearchResults(results);
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
}

function clearSearchResults() {
    document.getElementById('searchTerm').value = '';
    document.getElementById('searchResults').innerHTML = '';
}

// Event listener for real-time search
document.getElementById('searchTerm').addEventListener('input', searchStudents);
