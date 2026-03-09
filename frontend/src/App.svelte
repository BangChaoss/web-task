<script>
  import { onMount } from "svelte";
  import AnalogClock from "./AnalogClock.svelte";

  let now = $state(new Date());
  let displayMode = $state("Both");

  onMount(() => {
    const timer = setInterval(() => {
      now = new Date();
    }, 1000);
    return () => clearInterval(timer);
  });

  const locations = [
    { code: "VN", city: "Hanoi, Vietnam", tz: "Asia/Ho_Chi_Minh" },
    { code: "MM", city: "Yangon, Myanmar", tz: "Asia/Yangon" },
    { code: "KR", city: "Seoul, Korea", tz: "Asia/Seoul" },
    { code: "JP", city: "Tokyo, Japan", tz: "Asia/Tokyo" },
    { code: "SG", city: "Singapore", tz: "Asia/Singapore" },
    { code: "MY", city: "Kuala Lumpur, Malaysia", tz: "Asia/Kuala_Lumpur" },
  ];

  function getTimeForTimezone(date, timeZone) {
    const locString = date.toLocaleString("en-US", { timeZone });
    return new Date(locString);
  }

  function formatDigitalTime(date) {
    return date.toLocaleTimeString("en-US", { hour12: false });
  }

  function formatDate(date) {
    return date.toLocaleDateString("en-US", {
      weekday: "long",
      year: "numeric",
      month: "long",
      day: "numeric",
    });
  }
</script>

<svelte:head>
  <title>World Clock</title>
</svelte:head>

<div class="clock-app">
  <div class="header">
    <h1>🌍 World Clock</h1>
    <div class="display-mode">
      <label for="mode-select">Display Mode:</label>
      <select id="mode-select" bind:value={displayMode}>
        <option value="Both">Both</option>
        <option value="Digital">Digital</option>
        <option value="Analog">Analog</option>
      </select>
    </div>
  </div>

  <div class="clock-grid">
    {#each locations as loc}
      {@const localTime = getTimeForTimezone(now, loc.tz)}
      <div class="clock-card shadow">
        <h3 class="location">
          <span class="code">{loc.code}</span>
          {loc.city}
        </h3>

        {#if displayMode === "Both" || displayMode === "Digital"}
          <div class="digital-time">{formatDigitalTime(localTime)}</div>
          <div class="date">{formatDate(localTime)}</div>
        {/if}

        {#if displayMode === "Both"}
          <div class="analog-clock-wrapper">
            <AnalogClock time={localTime} />
          </div>
        {:else if displayMode === "Analog"}
          <!-- Provide more space when only analog is shown -->
          <div class="analog-clock-wrapper" style="margin-top: 30px;">
            <AnalogClock time={localTime} />
          </div>
        {/if}
      </div>
    {/each}
  </div>
</div>

<style>
  :global(body) {
    margin: 0;
    font-family: "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
  }
  .clock-app {
    min-height: 100vh;
    background: linear-gradient(135deg, #6b7aec 0%, #8751aa 100%);
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 30px 20px;
    color: white;
    box-sizing: border-box;
  }
  .header {
    text-align: center;
    margin-bottom: 30px;
  }
  h1 {
    margin: 0 0 15px 0;
    font-size: 2rem;
    font-weight: 700;
  }
  .display-mode {
    background: rgba(255, 255, 255, 0.3);
    padding: 6px 16px;
    border-radius: 20px;
    display: inline-flex;
    align-items: center;
    gap: 10px;
    font-size: 0.9rem;
    font-weight: 600;
  }
  select {
    border: none;
    border-radius: 12px;
    padding: 3px 10px;
    font-weight: bold;
    outline: none;
    cursor: pointer;
    background-color: white;
  }
  .clock-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px;
    max-width: 900px; /* Reduced width to match screenshot proportions better */
    width: 100%;
  }
  .clock-card {
    background: white;
    border-radius: 12px;
    padding: 25px 20px;
    text-align: center;
    color: #333;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .location {
    color: #5b79e8;
    font-size: 0.95rem;
    font-weight: 700;
    margin: 0 0 15px 0;
  }
  .location .code {
    text-transform: uppercase;
  }
  .digital-time {
    font-size: 1.8rem;
    font-weight: 700;
    color: #444;
    line-height: 1.2;
    margin-bottom: 5px;
    letter-spacing: 1px;
  }
  .date {
    font-size: 0.7rem;
    color: #888;
    margin-bottom: 15px;
  }
  .analog-clock-wrapper {
    margin-top: 10px;
    width: 100%;
    display: flex;
    justify-content: center;
  }

  @media (max-width: 900px) {
    .clock-grid {
      grid-template-columns: repeat(2, 1fr);
    }
  }
  @media (max-width: 600px) {
    .clock-grid {
      grid-template-columns: 1fr;
    }
  }
</style>
