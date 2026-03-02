import { defineConfig } from 'vite'
import { svelte } from '@sveltejs/vite-plugin-svelte'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [svelte()],
  server: {
    port: 5173,
    proxy: {
      // Vite will intercept requests to /api and send them to Spring Boot
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  }
})