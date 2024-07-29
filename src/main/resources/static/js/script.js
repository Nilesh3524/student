// function calculateCGPA() {
//     const sgpaInputs = document.querySelectorAll('.sgpa');
//     let totalSGPA = 0;
//     let count = 0;

//     sgpaInputs.forEach(input => {
//         const value = parseFloat(input.value);
//         if (!isNaN(value)) {
//             totalSGPA += value;
//             count++;
//         }
//     });

//     const cgpa = count > 0 ? (totalSGPA / count).toFixed(2) : 0;
//     document.getElementById('cgpa').value = cgpa;
// }

// function calculateAverageAttendance() {
//     const attendanceInputs = document.querySelectorAll('.attendance');
//     let totalAttendance = 0;
//     let count = 0;

//     attendanceInputs.forEach(input => {
//         const value = parseFloat(input.value);
//         if (!isNaN(value)) {
//             totalAttendance += value;
//             count++;
//         }
//     });

//     const averageAttendance = count > 0 ? (totalAttendance / count).toFixed(2) : 0;
//     document.getElementById('averageAttendance').value = averageAttendance;
// }

// function deleteStudent(id) {
//   swal({
//     title: "Are you sure?",
//     text: "You want delete this record !!",
//     icon: "warning",
//     buttons: true,
//     dangerMode: true,
//   }).then((willDelete) => {
//     if (willDelete) {
//       window.location = "/admin/student/delete/" + id;
//     } else {
//       swal("Your record is safe!");
//     }
//   });
// }
