import axios from "axios"
import cookie from 'react-cookies'

const BASE_URL = 'http://localhost:8080/GymHealth/api'

export const endpoints = {
    'register': '/users',
    'login': '/login',

    'profile': '/secure/profile',
    'update_profile': (userId) => `/secure/user/update/${userId}`,
    'delete_user': (userId) => `/users/${userId}`,

    'health_profile': '/secure/health-profiles',
    'add_health_profile': '/secure/health-profile/add',
    'update_health_profile': '/secure/health-profile/update',

    'training_package_list': '/secure/training-packages',
    'training_package_detail': (id) => `/secure/training-packages/${id}`,
    'create_training_package': '/secure/training-packages',
    'update_training_package': (id) => `/secure/training-packages/${id}`,
    'delete_training_package': (id) => `/secure/training-packages/${id}`,

    'statistics_week': '/secure/stats/week',
    'statistics_month': '/secure/stats/month',

};

export const authApis = () => {
    console.info(cookie.load('token'));
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': `Bearer ${cookie.load('token')}`,
            'Content-Type': 'application/json'
        }
    })
}

export default axios.create({
    baseURL: BASE_URL
});