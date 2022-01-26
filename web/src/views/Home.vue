<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>
  </div>
  <div>
    <a-button type="primary">
      Primary
    </a-button>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import { Button } from 'ant-design-vue';

export default defineComponent({
  name: 'Home',
  components: {
    HelloWorld,
  },
});
</script>
