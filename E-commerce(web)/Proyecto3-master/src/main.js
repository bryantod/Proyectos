import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/assets/css/bootstrap.min.css'
import '@/assets/js/bootstrap.bundle.min.js'
import spinnerload from '@/components/Commons/spinnerload.vue'
import Navbar from "@/components/Commons/Navbar.vue";
import FooterDefault from "@/components/Commons/FooterDefault.vue";
import '@/assets/global.scss';
import '@fortawesome/fontawesome-free/css/all.css';
import "toastr/build/toastr.min.css";



const app = createApp(App);
app.component('spinnerload', spinnerload);
app.component('Navbar', Navbar);
app.component('FooterDefault', FooterDefault);


app.use(store).use(router).mount('#app');
