const BASE_URL = "http://localhost:8080/api";

// Special login function - doesn't require token
export async function apiLogin(url, data) {
    try {
        const res = await fetch(BASE_URL + url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        if (!res.ok) {
            throw new Error(`API Error: ${res.status} ${res.statusText}`);
        }

        return await res.json();
    } catch (error) {
        console.error("API Login Error:", error);
        throw error;
    }
}

// GET
export async function apiGet(url) {
    const token = localStorage.getItem("token");
    if (!token) {
        window.location.href = "UserLogin.html"; // Temporarily disabled for testing
        throw new Error("No token found - please login");
    }

    try {
        const res = await fetch(BASE_URL + url, {
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (!res.ok) {
            if (res.status === 401) {
                localStorage.removeItem("token");
                window.location.href = "UserLogin.html";
                throw new Error("Unauthorized - Please login again");
            }
            throw new Error(`API Error: ${res.status} ${res.statusText}`);
        }

        return await res.json();
    } catch (error) {
        console.error("API GET Error:", error);
        throw error;
    }
}

// POST
export async function apiPost(url, data) {
    const token = localStorage.getItem("token");
    if (!token) {
        // window.location.href = "UserLogin.html"; // Temporarily disabled for testing
        throw new Error("No token found - please login");
    }

    try {
        const res = await fetch(BASE_URL + url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(data)
        });

        if (!res.ok) {
            if (res.status === 401) {
                localStorage.removeItem("token");
                window.location.href = "UserLogin.html";
                throw new Error("Unauthorized - Please login again");
            }
            throw new Error(`API Error: ${res.status} ${res.statusText}`);
        }

        return await res.json();
    } catch (error) {
        console.error("API POST Error:", error);
        throw error;
    }
}

// PUT
export async function apiPut(url, data) {
    const token = localStorage.getItem("token");
    if (!token) {
        // window.location.href = "UserLogin.html"; // Temporarily disabled for testing
        throw new Error("No token found - please login");
    }

    try {
        const res = await fetch(BASE_URL + url, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(data)
        });

        if (!res.ok) {
            if (res.status === 401) {
                localStorage.removeItem("token");
                window.location.href = "UserLogin.html";
                throw new Error("Unauthorized - Please login again");
            }
            throw new Error(`API Error: ${res.status} ${res.statusText}`);
        }

        return await res.json();
    } catch (error) {
        console.error("API PUT Error:", error);
        throw error;
    }
}

// DELETE
export async function apiDelete(url) {
    const token = localStorage.getItem("token");
    if (!token) {
        // window.location.href = "UserLogin.html"; // Temporarily disabled for testing
        throw new Error("No token found - please login");
    }

    try {
        const res = await fetch(BASE_URL + url, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (!res.ok) {
            if (res.status === 401) {
                localStorage.removeItem("token");
                window.location.href = "UserLogin.html";
                throw new Error("Unauthorized - Please login again");
            }
            throw new Error(`API Error: ${res.status} ${res.statusText}`);
        }

        return null;
    } catch (error) {
        console.error("API DELETE Error:", error);
        throw error;
    }
}
