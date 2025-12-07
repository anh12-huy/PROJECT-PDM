import { apiGet } from "./api.js";

export async function loadDashboardCharts() {

    const employees = await apiGet("/employees");
    const department = await apiGet("/department");

    // Tính số nhân viên mỗi phòng ban
    let deptCount = {};
    employees.forEach(e => {
        deptCount[e.departmentId] = (deptCount[e.departmentId] || 0) + 1;
    });

    const labels = department.map(d => d.departmentName);
    const data = department.map(d => deptCount[d.departmentID] || 0);

    new Chart(document.getElementById("deptChart"), {
        type: "bar",
        data: {
            labels,
            datasets: [{
                label: "Employees",
                data,
                backgroundColor: "rgba(54,162,235,0.6)"
            }]
        }
    });
}
