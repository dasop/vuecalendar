<template>
  <v-app>
    <v-main>
      <Calendars/>
    </v-main>
  </v-app>
</template>

<script>
import Calendars from './components/Calendars';

export default {
  name: 'App',

  components: {
    Calendars,
  },

  data: () => ({
    //
  }),
};
</script>
