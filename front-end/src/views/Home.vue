<!--<template>
  <div class="home">
    <h1>HOME</h1>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
}
</script>-->

<template>
  <v-row class="fill-height">
    <v-col>
      <v-sheet height="64">
        <v-toolbar
          flat
        >
          <v-btn
            outlined
            class="mr-4"
            color="grey darken-2"
            @click="setToday"
          >
            Today
          </v-btn>
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="prev"
          >
            <v-icon small>
              mdi-chevron-left
            </v-icon>
          </v-btn>
          <v-btn
            fab
            text
            small
            color="grey darken-2"
            @click="next"
          >
            <v-icon small>
              mdi-chevron-right
            </v-icon>
          </v-btn>
          <v-toolbar-title v-if="$refs.calendar">
            {{ $refs.calendar.title }}
          </v-toolbar-title>
          <v-spacer></v-spacer>
          <v-menu
            bottom
            right
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                outlined
                color="grey darken-2"
                v-bind="attrs"
                v-on="on"
              >
                <span>{{ typeToLabel[type] }}</span>
                <v-icon right>
                  mdi-menu-down
                </v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-list-item @click="type = 'day'">
                <v-list-item-title>Day</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'week'">
                <v-list-item-title>Week</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = 'month'">
                <v-list-item-title>Month</v-list-item-title>
              </v-list-item>
              <v-list-item @click="type = '4day'">
                <v-list-item-title>4 days</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
        </v-toolbar>
      </v-sheet>
      <v-sheet height="600">
        <v-calendar
          ref="calendar"
          v-model="focus"
          color="primary"
          :events="events"
          :event-color="getEventColor"
          :type="type"
          @click:event="showEvent"
          @click:more="viewDay"
          @click:date="addEvent"
          @change="updateRange"
        ></v-calendar>
        <v-menu
          v-model="selectedOpen"
          :close-on-content-click="false"
          :activator="selectedElement"
          offset-x
        >
          <v-card
            color="grey lighten-4"
            min-width="350px"
            flat
          >
            <v-toolbar
              :color="selectedEvent.color"
              dark
            >
              <v-btn icon>
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
              <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>
              <v-btn icon>
                <v-icon>mdi-dots-vertical</v-icon>
              </v-btn>
            </v-toolbar>
            <v-card-text>
              <span v-html="selectedEvent.details"></span>
            </v-card-text>
            <v-card-actions>
              <v-btn
                text
                color="secondary"
                @click="selectedOpen = false"
              >
                Cancel
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-menu>
        <v-dialog max-width="600px" v-model="popup">
          <v-card>
            <v-card-title>
              <h2> Add a New Event</h2>
            </v-card-title>
            <v-card-text>
              <v-form calss = "px-3" ref="form">
                <v-text-field label = "Title" v-model = "title" prepend-icon = "mdi-folder" :rules="inputRules"></v-text-field>
                <v-textarea label = "Information" v-model = "content" prepend-icon="mdi-pencil" :rules="inputRules"></v-textarea>
                <v-row>
                  <v-col cols="6" class="pb-0">
                    <v-menu>
                      <template v-slot:activator="{on}">
                        <v-text-field slot="activator" label="start date"
                          readonly
                          prepend-icon="mdi-calendar-month"
                          v-on="on"
                          :value="startDate"></v-text-field>                      
                      </template>
                      <v-date-picker v-model="startDate"></v-date-picker>
                    </v-menu>
                  </v-col>
                  <v-col cols="6" class="pb-0">
                    <v-menu>
                      <template v-slot:activator="{on}">
                        <v-text-field slot="activator" label="end date"
                          readonly
                          prepend-icon="mdi-calendar-month"
                          v-on="on"
                          :value="endDate"></v-text-field>                      
                      </template>
                      <v-date-picker v-model = "endDate" :allowed-dates="allowedDates"></v-date-picker>
                    </v-menu>
                  </v-col>
                </v-row>
                <v-btn text class = "success" mx-0 mt-3 @click = "submit" :loading="loading">Add event</v-btn>
              </v-form>
            </v-card-text>
          </v-card>>
        </v-dialog>
      </v-sheet>
    </v-col>
  </v-row>
</template>

<script>
  import axios from 'axios';

  export default {
    data: () => ({
      focus: '',
      type: 'month',
      typeToLabel: {
        month: 'Month',
        week: 'Week',
        day: 'Day',
        '4day': '4 Days',
      },
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      events: [],
      colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
      names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
      popup: false,
      title: '',
      content: '',
      startDate: '',
      endDate: '',
      inputRules:[
        v => v.length >= 3 || 'Minimun length is 3 characters'
      ],
      loading : false
    }),
    mounted () {
      this.$refs.calendar.checkChange()
    },
    methods: {
      viewDay ({ date }) {
        this.focus = date
        this.type = 'day'
      },
      addEvent({date}){
        this.popup = true
        this.startDate = date
        this.Date = date
      },
      getEventColor (event) {
        return event.color
      },
      setToday () {
        this.focus = ''
      },
      prev () {
        this.$refs.calendar.prev()
      },
      next () {
        this.$refs.calendar.next()
      },
      showEvent ({ nativeEvent, event }) {
        const open = () => {
          this.selectedEvent = event
          this.selectedElement = nativeEvent.target
          setTimeout(() => {
            this.selectedOpen = true
          }, 10)
        }

        if (this.selectedOpen) {
          this.selectedOpen = false
          setTimeout(open, 10)
        } else {
          open()
        }

        nativeEvent.stopPropagation()
      },
      updateRange ({ start, end }) {
        const events = []

        const min = new Date(`${start.date}T00:00:00`)
        const max = new Date(`${end.date}T23:59:59`)
        console.log(`min:${min}, max:${max}`)
        console.log(`start.date:${start.date}, end.date:${end.date}`)

        axios({
          method:'post',
          url:'/api/getevent',
          data:{
            start:start.date,
            end:end.date
          }
        }).then(({data})=>{
          console.log(data);

          const dbevents = data.events;

          console.log(dbevents);

          dbevents.forEach(event =>{
            events.push({
            name: event.title,
            start: event.start,
            end: event.end,
            color: this.colors[this.rnd(0, this.colors.length - 1)]
            })
          })

           this.events = events
        })


        // const days = (max.getTime() - min.getTime()) / 86400000
        // const eventCount = this.rnd(days, days + 20)

        // for (let i = 0; i < eventCount; i++) {
        //   const allDay = this.rnd(0, 3) === 0
        //   const firstTimestamp = this.rnd(min.getTime(), max.getTime())
        //   const first = new Date(firstTimestamp - (firstTimestamp % 900000))
        //   const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000
        //   const second = new Date(first.getTime() + secondTimestamp)

        //   console.log(`first:${first}, second:${second}, allDay:${allDay}`)

        //   events.push({
        //     name: this.names[this.rnd(0, this.names.length - 1)],
        //     start: first,
        //     end: second,
        //     color: this.colors[this.rnd(0, this.colors.length - 1)],
        //     timed: !allDay,
        //   })
        // }

        // this.events = events
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
      },
      submit(){
        if(this.$refs.form.validate()){
          console.log(this.title, this.content)
          this.loading = true;
          axios({
            method: 'post',
            url: '/api/setevent',
            data: {
              title : this.title,
              details : this.content,
              start : this.startDate,
              end : this.endDate,
              name : 'test',
              team : '결제플랫폼팀'
              }
          }).then(()=>{
            this.loading = false;
            this.popup = false;
          })
        }
        
      },
      allowedDates(val) {
        let endDate = val.split('-').reduce((a, b) => a + b);
        let startDate = this.startDate.split('-').reduce((a, b) => a + b);
        return endDate >= startDate;
      }
    },
  }
</script>


