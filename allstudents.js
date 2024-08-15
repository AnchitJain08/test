function displayAllStudents() {
    // In a real application, this would be an API call to your Java backend
    // For now, we'll use the students array from studentSearch.js
    let tableBody = document.getElementById('studentsTableBody');
    tableBody.innerHTML = '';

    students.forEach(student => {
        let row = tableBody.insertRow();
        row.innerHTML = `
            <td class="border p-2">${student.studentNo}</td>
            <td class="border p-2">${student.studentName}</td>
            <td class="border p-2">${student.email}</td>
            <td class="border p-2">${student.phone}</td>
            <td class="border p-2">${student.course}</td>
        `;
    });
}

function addStudent(event) {
    event.preventDefault();
    let newStudent = {
        studentNo: document.getElementById('studentNo').value,
        studentName: document.getElementById('studentName').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('phone').value,
        course: document.getElementById('course').value
    };

    // In a real application, this would be an API call to your Java backend
    students.push(newStudent);

    // Clear the form
    document.getElementById('addStudentForm').reset();

    // Refresh the student list
    displayAllStudents();

    alert('Student added successfully!');
}

// Add event listener to the form
document.getElementById('addStudentForm').addEventListener('submit', addStudent);

// Initial display of all students
displayAllStudents();
