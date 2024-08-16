import React from 'react';

const CourseTable = () => (
  <table className="w-full border-collapse mb-8">
    <thead>
      <tr className="bg-gray-200">
        <th className="border p-2">Group</th>
        <th className="border p-2">Course Code</th>
        <th className="border p-2">Course Title</th>
        <th className="border p-2">Course Type</th>
        <th className="border p-2">L T P J</th>
        <th className="border p-2">Credit</th>
        <th className="border p-2">Course Category</th>
        <th className="border p-2">Course Option</th>
        <th className="border p-2">Class No.</th>
        <th className="border p-2">Slot</th>
        <th className="border p-2">Venue</th>
        <th className="border p-2">Faculty</th>
        <th className="border p-2">Status</th>
      </tr>
    </thead>
    <tbody>
      {[
        { code: 'CSE2003', title: 'Computer Architecture and Organization', type: 'Lecture and Tutorial Hours Only', credit: '4.0', category: 'Programme Core', option: 'Regular', classNo: 'BL202425D400563', slot: 'B14+F11+F12', venue: '105', faculty: 'JAY PRAKASH MAURYA - SCOPE', status: 'Registered and Approved' },
        { code: 'CSE2004', title: 'Theory Of Computation And Compiler Design', type: 'Lecture and Tutorial Hours Only', credit: '4.0', category: 'Programme Core', option: 'Regular', classNo: 'BL202425D400585', slot: 'B11+B12+B13', venue: '207', faculty: 'AZRA NAZIR - SCOPE', status: 'Registered and Approved' },
        { code: 'CSE3004', title: 'Design and Analysis of Algorithms', type: 'Lecture and Tutorial Hours Only', credit: '4.0', category: 'Programme Core', option: 'Regular', classNo: 'BL202425D400619', slot: 'A11+A12+A13', venue: 'CR-024', faculty: 'MOHAMMAD SULTAN - SCOPE', status: 'Registered and Approved' },
        { code: 'CSE3005', title: 'Software Engineering', type: 'Lecture and Tutorial practical hours only', credit: '4.0', category: 'Programme Core', option: 'Regular', classNo: 'BL202425D400623', slot: 'C14+E11+E12', venue: 'ARCH-002', faculty: 'MANORMA CHOUHAN - SCOPE', status: 'Registered and Approved' },
        { code: 'ECE3004', title: 'Microprocessors And Microcontrollers', type: 'Lecture and Tutorial practical hours only', credit: '4.0', category: 'Programme Core', option: 'Regular', classNo: 'BL202425D400226', slot: 'A21+A22+A23', venue: 'CR-004', faculty: 'MAYANK GUPTA - SEEE', status: 'Registered and Approved' },
        { code: 'HUM0001', title: 'Ethics And Values', type: 'Lecture and Tutorial Hours Only', credit: '2.0', category: 'Open Elective', option: 'Regular', classNo: 'BL202425D400555', slot: 'E21', venue: 'CR-005', faculty: 'RAJENDRA MAHANANDIA - VITBS', status: 'Registered and Approved' },
        { code: 'MAT2003', title: 'Applied Numerical Method', type: 'Lecture and Tutorial Hours Only', credit: '3.0', category: 'University Elective - Natural Science Electives (UENSE)', option: 'Re-registration', classNo: 'BL202425D400026', slot: 'E14+F14', venue: 'CR-013', faculty: 'MAMTA AGRAWAL - SASL', status: 'Registered and Invoice Not Generated' },
      ].map((course, index) => (
        <tr key={index} className={index % 2 === 0 ? 'bg-gray-100' : ''}>
          <td className="border p-2">General 2020 /2022</td>
          <td className="border p-2">{course.code}</td>
          <td className="border p-2">{course.title}</td>
          <td className="border p-2">{course.type}</td>
          <td className="border p-2">0 0 0 0</td>
          <td className="border p-2">{course.credit}</td>
          <td className="border p-2">{course.category}</td>
          <td className="border p-2">{course.option}</td>
          <td className="border p-2">{course.classNo}</td>
          <td className="border p-2">{course.slot}</td>
          <td className="border p-2">{course.venue}</td>
          <td className="border p-2">{course.faculty}</td>
          <td className="border p-2" style={{color: course.status.includes('Invoice') ? 'red' : 'green'}}>{course.status}</td>
        </tr>
      ))}
    </tbody>
  </table>
);

const TimeTable = () => {
  const timeSlots = ['08:30', '10:05', '11:40', 'Lunch', '13:15', '14:50', '16:25', '18:00'];
  const endTimes = ['10:00', '11:35', '13:10', 'Lunch', '14:45', '16:20', '17:55', '19:30'];
  const days = ['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT'];
  const schedule = [
    ['A11-CSE3004-LT-CR-024', 'B11-CSE2004-LT-207', 'C11', 'Lunch', 'A21-ECE3004-LTP-CR-004', 'A14', 'B21', 'C21'],
    ['D11', 'E11-CSE3005-LTP-ARCH-002', 'F11-CSE2003-LT-105', 'Lunch', 'D21', 'E14-MAT2003-LT-CR-013', 'E21-HUM0001-LT-CR-005', 'F21'],
    ['A12-CSE3004-LT-CR-024', 'B12-CSE2004-LT-207', 'C12', 'Lunch', 'A22-ECE3004-LTP-CR-004', 'B14-CSE2003-LT-105', 'B22', 'A24'],
    ['D12', 'E12-CSE3005-LTP-ARCH-002', 'F12-CSE2003-LT-105', 'Lunch', 'D22', 'F14-MAT2003-LT-CR-013', 'E22', 'F22'],
    ['A13-CSE3004-LT-CR-024', 'B13-CSE2004-LT-207', 'C13', 'Lunch', 'A23-ECE3004-LTP-CR-004', 'C14-CSE3005-LTP-ARCH-002', 'B23', 'B24'],
    ['D13', 'E13', 'F13', 'Lunch', 'D23', 'D14', 'D24', 'E23']
  ];

  const getCellColor = (text) => {
    if (text.includes('CSE3004')) return '#FFB3BA'; // Light Pink
    if (text.includes('CSE2004')) return '#BAFFC9'; // Light Mint
    if (text.includes('CSE2003')) return '#BAE1FF'; // Light Sky Blue
    if (text.includes('CSE3005')) return '#FFFFBA'; // Light Yellow
    if (text.includes('ECE3004')) return '#FFD9BA'; // Light Peach
    if (text.includes('HUM0001')) return '#E0BBE4'; // Light Lavender
    if (text.includes('MAT2003')) return '#D4F0F0'; // Light Cyan
    return 'white';
  };

  return (
    <div>
      <table className="w-full border-collapse">
        <thead>
          <tr className="bg-gray-200">
            <th className="border p-2">Theory</th>
            {timeSlots.map((slot, index) => (
              <th key={index} className="border p-2">
                <div>Start {slot}</div>
                <div>End {endTimes[index]}</div>
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {days.map((day, dayIndex) => (
            <tr key={day}>
              <td className="border p-2 font-bold">{day}</td>
              {schedule[dayIndex].map((slot, slotIndex) => (
                <td key={slotIndex} className="border p-2 text-center" style={{backgroundColor: getCellColor(slot)}}>
                  {slot}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
      <p className="mt-4 text-sm">Registered Slots / Hours are highlighted with green color.</p>
      <p className="text-sm">If there is no green highlight it indicates that there are no Registered List Slots / Hours.</p>
    </div>
  );
};

const CourseScheduleAndTimetable = () => (
  <div className="p-4">
    <CourseTable />
    <TimeTable />
  </div>
);

export default CourseScheduleAndTimetable;
