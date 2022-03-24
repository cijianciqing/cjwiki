<template>
    <a-layout id="components-layout-demo-top-side">
        <a-layout-header class="header">
            <GlobalHeader/>
        </a-layout-header>
        <a-layout-content style="padding: 0 50px">

        </a-layout-content>
    </a-layout>
</template>
<script lang="ts">
    import GlobalHeader from "@/components/GlobalHeader.vue";
    import {defineComponent, onMounted, reactive, ref, toRef, watch} from 'vue';
    import axios from "axios";
    import {message} from "ant-design-vue";

    export default defineComponent({
        components: {
            GlobalHeader,
        },


    });
</script>

