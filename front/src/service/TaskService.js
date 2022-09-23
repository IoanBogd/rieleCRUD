import axios from 'axios';


export default class TaskService {

    url = "http://localhost:8080/task/";

    getAll() {
        return axios.get(this.url + "all");
    }

    save(task) {
        return axios.post(this.url + "save", task);
    }

    delete(id) {
        return axios.get(this.url + "delete/"+id);
    }
}