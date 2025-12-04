// Lấy user từ localStorage
export function getUser() {
    const u = localStorage.getItem("user");
    return u ? JSON.parse(u) : null;
}

// Check login
export function checkLogin() {
    if (!localStorage.getItem("token")) {
        window.location.href = "UserLogin.html";
    }
}

// Check Role: Admin / User
export function checkRole(requiredRoles) {
    const user = getUser();
    if (!user) return window.location.href = "UserLogin.html";

    const allowedRoles = Array.isArray(requiredRoles) ? requiredRoles : [requiredRoles];
    if (requiredRoles && !allowedRoles.includes(user.role)) {
        alert("You do not have access to this page!");
        if (user.role === "EMPLOYEE") {
            window.location.href = "Attendance.html";
        } else {
            window.location.href = "Dashboard.html";
        }
    }
}

// Update user info in topbar
export function updateUserInfo() {
    const user = getUser();
    if (user) {
        const nameEl = document.getElementById("userName");
        const roleEl = document.getElementById("userRole");
        const avatarEl = document.getElementById("userAvatar");
        if (nameEl) nameEl.innerText = user.username;
        if (roleEl) roleEl.innerText = user.role;
        if (avatarEl) avatarEl.innerText = user.username.charAt(0).toUpperCase();
    }
}

// Logout
export function logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    window.location.href = "UserLogin.html";
}

// Login Function
import { apiLogin } from "./api.js";

export async function login(username, password) {
    const res = await apiLogin("/auth/login", { username, password });

    if (res.token) {
        localStorage.setItem("token", res.token);
        localStorage.setItem("userId", res.userId);
        // Create a basic user object if not provided by backend
        const user = res.user || { userId: res.userId, username: username, role: res.role };
        localStorage.setItem("user", JSON.stringify(user));

        window.location.href = "Dashboard.html";
    } else {
        alert("Incorrect username or password.");
    }
}
