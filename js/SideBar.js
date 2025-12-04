import { getUser, logout } from "./App.js";

window.addEventListener("DOMContentLoaded", () => {
    highlightMenu();
    applyRoleMenu();
    addLogoutEvent();
});

// Tô màu menu hiện tại
function highlightMenu() {
    const current = window.location.pathname.split("/").pop();
    document.querySelectorAll(".menu-item").forEach(a => {
        if (a.getAttribute("href") === current) {
            a.classList.add("bg-gray-700", "text-white", "rounded", "px-3");
        }
    });
}

// Ẩn menu theo Role
function applyRoleMenu() {
    const user = getUser();
    if (!user) return;

        // EMPLOYEE can only see dashboard, attendance, employees (own), holidays, shifts, departments, leave records
        // MANAGER and ADMIN can see all
}

// logout btn
function addLogoutEvent() {
    const btn = document.getElementById("logoutBtn");
    if (btn) {
        btn.addEventListener("click", logout);
    }
}
