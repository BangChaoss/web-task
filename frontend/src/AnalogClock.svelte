<script>
    let { time } = $props();

    let hours = $derived(time.getHours());
    let minutes = $derived(time.getMinutes());
    let seconds = $derived(time.getSeconds());

    let hourAngle = $derived((hours % 12) * 30 + minutes * 0.5);
    let minuteAngle = $derived(minutes * 6 + seconds * 0.1);
    let secondAngle = $derived(seconds * 6);
</script>

<div class="clock">
    <div class="clock-face">
        {#each Array.from({ length: 12 }, (_, i) => i + 1) as num}
            <div
                class="number-container"
                style="transform: rotate({num * 30}deg)"
            >
                <span class="number" style="transform: rotate({-num * 30}deg)"
                    >{num}</span
                >
            </div>
        {/each}
    </div>
    <div class="hands">
        <div class="hand hour" style="transform: rotate({hourAngle}deg)"></div>
        <div
            class="hand minute"
            style="transform: rotate({minuteAngle}deg)"
        ></div>
        <div
            class="hand second"
            style="transform: rotate({secondAngle}deg)"
        ></div>
        <div class="center-dot"></div>
    </div>
</div>

<style>
    .clock {
        width: 130px;
        height: 130px;
        border: 3px solid #5b79e8;
        border-radius: 50%;
        position: relative;
        margin: 0 auto;
        background: white;
    }
    .clock-face {
        width: 100%;
        height: 100%;
        position: absolute;
        top: 0;
        left: 0;
    }
    .number-container {
        position: absolute;
        width: 100%;
        height: 100%;
        text-align: center;
    }
    .number {
        display: inline-block;
        padding-top: 6px;
        font-size: 13px;
        color: #5b79e8;
        font-weight: bold;
    }
    .hands {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
    }
    .hand {
        position: absolute;
        bottom: 50%;
        left: 50%;
        transform-origin: bottom center;
        border-radius: 4px;
    }
    .hour {
        width: 4px;
        height: 35px;
        background-color: #333;
        margin-left: -2px;
        z-index: 2;
    }
    .minute {
        width: 3px;
        height: 45px;
        background-color: #666;
        margin-left: -1.5px;
        z-index: 3;
    }
    .second {
        width: 2px;
        height: 52px;
        background-color: #ed4040;
        margin-left: -1px;
        z-index: 4;
    }
    .center-dot {
        position: absolute;
        top: 50%;
        left: 50%;
        width: 8px;
        height: 8px;
        background-color: #5b79e8;
        border-radius: 50%;
        transform: translate(-50%, -50%);
        z-index: 5;
    }
</style>
