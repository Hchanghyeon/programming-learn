import axios from 'axios';

const axiosInstance = axios.create({
    baseURL: "/api",
    headers: {
        'Content-Type' : 'application/json',
    },
    timeout: 5000
});

export default axiosInstance;