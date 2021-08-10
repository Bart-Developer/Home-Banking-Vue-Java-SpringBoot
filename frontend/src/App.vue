<template>
  <router-view />
</template>

<script>

import { useRouter } from 'vue-router';
export default {
  
  mounted() {

      const router = useRouter();
	
      this.$store.commit('setLogin', JSON.parse(sessionStorage.getItem("SESSIONSTATUS")))

      if (this.$store.state.login != "ok"){
			  router.replace('/login');
		  }
}
}
</script>

<style>

</style>
