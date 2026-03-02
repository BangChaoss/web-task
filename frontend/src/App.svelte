<script>
  import { onMount } from 'svelte';

  // Navigation State
  let activePage = $state('home'); // can be 'home' or 'auth'

  // Auth Switch State
  let isLogin = $state(true);
  let email = $state('');
  let password = $state('');

  // Scroll-to-Top Logic
  let scrollY = $state(0);

  onMount(() => {
    const handleScroll = () => {
      scrollY = window.scrollY;
    };
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  });

  function scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'instant' });
  }

  // Handle Login/Signup Switch
  function toggleAuthForm() {
    isLogin = !isLogin;
    email = '';
    password = '';
  }

  // Handle Form Submission (Calls Spring Boot via Vite Proxy)
  async function handleAuthSubmit(e) {
    e.preventDefault();
    // Because of vite.config.js, this routes to http://localhost:8080/api/auth/...
    const endpoint = isLogin ? '/api/auth/login' : '/api/auth/signup';

    try {
      const response = await fetch(endpoint, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' }
      });

      const message = await response.text();

      // 4. Show Alert requirement
      alert(message);

      if (isLogin) {
        // "Welcome to home page!" -> Go back to Home
        activePage = 'home';
      } else {
        // "You registered successfully!" -> Switch to login form
        isLogin = true;
      }
    } catch (error) {
      console.error(error);
      alert("Error connecting to server. Is Spring Boot running?");
    }
  }
</script>

<div class="d-flex flex-column min-vh-100">

  <!-- 1. NAVBAR -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <div class="container">
      <a class="navbar-brand fw-bold" href="#" onclick={(e) => { e.preventDefault(); activePage = 'home'; }}>Kiet</a>

      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <a class="nav-link {activePage === 'home' ? 'active' : ''}" href="#" onclick={(e) => { e.preventDefault(); activePage = 'home'; }}>Home</a>
          </li>
          <li class="nav-item"><a class="nav-link" href="#">About</a></li>
          <li class="nav-item"><a class="nav-link" href="#">Contact</a></li>
        </ul>
        <!-- Login Button Right Aligned -->
        <button class="btn btn-outline-light ms-auto" onclick={() => activePage = 'auth'}>
          Login / Signup
        </button>
      </div>
    </div>
  </nav>

  <!-- MAIN CONTENT AREA -->
  <main class="flex-grow-1">

    {#if activePage === 'home'}
      <!-- 2. HERO SECTION -->
      <div class="bg-light text-dark py-5 text-center shadow-sm d-flex align-items-center" style="min-height: 60vh;">
        <div class="container">
          <h1 class="display-3 fw-bold mb-3">Welcome to Kiet Website</h1>
          <p class="lead mb-4 text-secondary">Pham Tuan Kiet. He provide the best solutions.</p>
          <button class="btn btn-primary btn-lg px-4 shadow" onclick={() => activePage = 'auth'}>Become member of Kiet now!</button>
        </div>
      </div>

      <!-- Dummy content to force scrolling -->
      <div class="container text-center py-5" style="height: 100vh;">
        <h4 class="text-muted mt-5">Scroll down to see the Scroll-to-Top Arrow</h4>
      </div>

    {:else if activePage === 'auth'}
      <!-- 4. LOGIN / SIGNUP SECTION -->
      <div class="container py-5">
        <div class="row justify-content-center">
          <div class="col-md-5">
            <div class="card shadow border-0">
              <div class="card-body p-5">
                <h2 class="text-center mb-4">{isLogin ? 'Login' : 'Sign Up'}</h2>

                <form onsubmit={handleAuthSubmit}>
                  <div class="mb-3">
                    <label class="form-label" for="email">Email address</label>
                    <input type="email" id="email" class="form-control" bind:value={email} required />
                  </div>
                  <div class="mb-4">
                    <label class="form-label" for="password">Password</label>
                    <input type="password" id="password" class="form-control" bind:value={password} required />
                  </div>
                  <button type="submit" class="btn btn-primary w-100 mb-3">
                    {isLogin ? 'Login' : 'Sign Up'}
                  </button>
                </form>

                <div class="text-center mt-3">
                  <p class="mb-0">
                    {isLogin ? "Don't have an account?" : "Already have an account?"}
                    <!-- Switch Function -->
                    <button class="btn btn-link p-0 text-decoration-none" onclick={toggleAuthForm}>
                      {isLogin ? 'Sign up here' : 'Login here'}
                    </button>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    {/if}

  </main>

  <!-- 3. FOOTER -->
  <footer class="bg-dark text-white text-center py-4 mt-auto">
    <div class="container">
      <p class="mb-2">&copy; 2027 KietWebpage. All Rights Reserved.</p>
      <div>
        <a href="#" class="text-white me-3"><i class="bi bi-facebook fs-5"></i></a>
        <a href="#" class="text-white me-3"><i class="bi bi-twitter fs-5"></i></a>
        <a href="#" class="text-white"><i class="bi bi-instagram fs-5"></i></a>
      </div>
    </div>
  </footer>

  <!-- SCROLL TO TOP ARROW -->
  {#if scrollY > 200}
    <button
            class="btn btn-primary rounded-circle position-fixed bottom-0 end-0 m-4 shadow-lg"
            style="width: 50px; height: 50px; z-index: 1000;"
            onclick={scrollToTop}>
      <i class="bi bi-arrow-up"></i>
    </button>
  {/if}
</div>