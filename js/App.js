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
export function checkRole(requiredRole) {
    const user = getUser();
    if (!user) return window.location.href = "UserLogin.html";

    if (requiredRole && user.role !== requiredRole) {
        alert("You do not have access!");
        window.location.href = "Dashboard.html";
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
        const user = res.user || { userId: res.userId, username: username };
        localStorage.setItem("user", JSON.stringify(user));

        window.location.href = "Dashboard.html";
    } else {
        alert("Incorrect username or password.");
    }
}
