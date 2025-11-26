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

    if (user.role === "User") {
        document.querySelector("[data-menu='employees']").style.display = "none";
        document.querySelector("[data-menu='department']").style.display = "none";
        document.querySelector("[data-menu='shifts']").style.display = "none";
        document.querySelector("[data-menu='holiday']").style.display = "none";
    }
}

// logout btn
function addLogoutEvent() {
    const btn = document.getElementById("logoutBtn");
    if (btn) {
        btn.addEventListener("click", logout);
    }
}
