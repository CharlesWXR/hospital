import { createRouter, createWebHashHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import NavigationView from '../views/NavigationView'
import FailView from '../views/FailView'
import DiagnosisResultView from '../views/DiagnosisResultView'

const routes = [
	{
		path: '/:token',
		name: 'home',
		component: LoginView,
	},
	{
		path: '/route',
		name: 'route',
		component: NavigationView,
	},
	{
		path: '/fail',
		name: 'fail',
		component: FailView,
	},
	{
		path: '/diagnosisResult',
		name: 'diagnosisResult',
		component: DiagnosisResultView,
	}
]

const router = createRouter({
	history: createWebHashHistory(),
	routes
})

export default router
