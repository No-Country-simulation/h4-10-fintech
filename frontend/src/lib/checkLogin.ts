export async function checkAuthentication() {
  const urlParams = new URLSearchParams(window.location.search);
  const loggedIn = urlParams.get("loggedIn");

  // Solo consultar si el usuario acaba de loguearse
  if (loggedIn) {
    try {
      const response = await fetch(
        "https://h4-10-fintech.onrender.com/api/auth/is-authenticated",
        {
          credentials: "include", // Permitir cookies de sesión
        }
      );
      const isAuthenticated = await response.json();

      if (isAuthenticated) {
        // Si está autenticado, obtener el custom token
        const tokenResponse = await fetch(
          "https://h4-10-fintech.onrender.com/api/auth/getToken",
          {
            credentials: "include",
          }
        );
        if (tokenResponse.ok) {
          const data = await tokenResponse.json();
          return data.token;
          window.history.replaceState({}, document.title, "/dashboard"); // Limpiar la URL
        }
      } else {
        window.location.href = "/"; // Redirigir si no está autenticado
      }
    } catch (error) {
      console.error("Error verificando autenticación:", error);
      window.location.href = "/";
    }
  }
}
