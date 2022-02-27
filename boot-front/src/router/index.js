import { createRouter, createWebHashHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import NavigationView from '../views/NavigationView.vue'
import FailView from '../views/FailView.vue'
import DiagnosisResultView from '../views/DiagnosisResultView.vue'
import DocDiagnosisView from '../views/DocDiagnosisView.vue'

const routes = [
	{
		path: '/:token',
		name: 'home',
		component: LoginView,
	},
	{
		path: '/navigator',
		name: 'navigator',
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
	},
	{
		path: '/docDiagnosis',
		name: 'DocDiagnosis',
		component: DocDiagnosisView,
	}
]

const router = createRouter({
	history: createWebHashHistory(),
	routes
})

export default router
