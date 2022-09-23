<template>
  <div style="margin: 0 auto; width: 80%">
    <Toast />
    <Panel header="TASKS">
      <Menubar :model="items" />
      <br />
      <DataTable :value="tasks" :paginator="true" :rows="10" selectionMode="single" :selection.sync="selectedTask" dataKey="id">
        <Column field="id" header="ID"></Column>
        <Column field="name" header="NAME"></Column>
        <Column field="state" header="STATUS"></Column>
      </DataTable>
    </Panel>
    <Dialog header="Crear Tarea" :visible.sync="displayModal" :modal="true">
      <span class="p-float-label">
        <InputText id="name" type="text" v-model="task.name" style="width: 100%" />
        <label for="name">NAME</label>
      </span>
      <br />
      <span class="p-float-label">
        <InputText id="state" type="text" v-model="task.state" style="width: 100%" />
        <label for="state">STATUS</label>
      </span>
   
      <template #footer>
        <Button label="Save" icon="pi pi-check" @click="save" />
        <Button label="Cancel" icon="pi pi-times" @click="closeModal" class="p-button-secondary" />
      </template>
    </Dialog>
  </div>
</template>

<script>
import TaskService from "../service/TaskService";
export default {
  name: "CrudApp",
  data() {
    return {
      tasks: null,
      task: {
        id: null,
        name: null,
        state: null,
      },
      selectedTask: {
        id: null,
        name: null,
        state: null,
      },
      items: [
        {
          label: "New",
          icon: "pi pi-fw pi-plus",
          command: () => {
            this.showSaveModal();
          }
        },
        {
          label: "Edit",
          icon: "pi pi-fw pi-pencil",
          command: () => {
            this.showEditModal();
          }
        },
        {
          label: "Delete",
          icon: "pi pi-fw pi-trash",
          command: () => {
            this.delete();
          }

        },
        {
          label: "Refresh",
          icon: "pi pi-fw pi-refresh",
          command: () => {
            this.getAll();
          }
        }
      ],
      displayModal: false
    };
  },
  taskService: null,
  created() {
    this.taskService = new TaskService();
  },
  mounted() {
    this.getAll();
  },
  methods: {
    showSaveModal() {
      this.displayModal = true;
    },
    showEditModal(){
        this.task = this.selectedTask;
        this.displayModal = true;
    },
    getAll() {
      this.taskService.getAll().then(data => {
        this.tasks = data.data;
      });
    },
    delete(){
        if (confirm("Do you want to delete this task?")) {
        this.taskService.delete(this.selectedTask.id).then(data => {
            if(data.status === 200) {
                this.$toast.add({
                    severity:'success', 
                    summary: 'Warning!', 
                    detail: 'The task has been deleted',
                    life: 3000
                });
                this.getAll();
            }
        });
      }
    },
    save() {
      this.taskService.save(this.task).then(data => {
        if (data.status === 200) {
          this.displayModal = false;
          this.task = {
            name: null,
            state: null,
          };
          this.getAll();
        }
      });
    },
    closeModal() {
      this.displayModal = false;
    }
  }
};
</script>

<style>
</style>
