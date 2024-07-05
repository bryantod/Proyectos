import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('@/views/ProfileView.vue')
  },
  {
    path: '/planes',
    name: 'Planes',
    component: () => import('@/views/PlanesView.vue')
  },
  {
    path: '/WishList',
    name: 'Wish List',
    component: () => import('@/views/WishListView.vue')
  },
  {
    path: '/sign-up',
    name: 'Sign up',
    component: () => import('@/views/RegistroView.vue')
  },
  {
    path: '/sign-in',
    name: 'Sign in',
    component: () => import('@/views/LoginView.vue')
  },
  {
    path: '/cursos',
    name: 'cursos',
    component: () => import('@/views/CursosView.vue')
  },
  {
    path: '/upload',
    name: 'Upload',
    component: () => import('@/views/UploadView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
