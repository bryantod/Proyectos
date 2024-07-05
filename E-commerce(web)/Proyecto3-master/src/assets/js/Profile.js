import axios from "axios";
import { createClient } from '@supabase/supabase-js';
import toastr from 'toastr';

const supabase = createClient('https://tnbzjbceklpojzikytcj.supabase.co', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRuYnpqYmNla2xwb2p6aWt5dGNqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQ1MDc4MjMsImV4cCI6MjAzMDA4MzgyM30.j63f7-fy2z5WRSKwHHooQVJz-xM2j1XPqKek6oxhYow');

export default {
  name: "Profile",
  data() {
    return {
      profile: {},
      token: localStorage.getItem("token"),
      imagen: null,
      mostrarModalEditar: false
    };
  },
  methods: {
    async fetch() {
      try {
        const res = await axios.get(`http://localhost:9999/Perfil/usuario/${this.token}`);
        this.profile = res.data;
        console.log(res.data);
      } catch (err) {
        console.log("Error al buscar lista de deseos", err);
      }
    },
    async uploadImage() {
      try {
        if (this.imagen) {
          console.log('Nombre del archivo:', this.imagen.name);

          const { data, error } = await supabase.storage
            .from('usuarios')
            .upload(`public/img/${encodeURIComponent(this.imagen.name)}`, this.imagen);

          if (error) {
            throw error;
          }

          return `https://tnbzjbceklpojzikytcj.supabase.co/storage/v1/object/public/usuarios/public/img/${encodeURIComponent(this.imagen.name)}`;
        }
      } catch (error) {
        console.error('Error al subir imagen:', error.message);
        toastr.error('Ocurrió un error al subir la imagen');
      }
      return null;
    },
    async updateProfile() {
      try {
        let imageUrl = this.profile.img;
        if (this.imagen) {
          imageUrl = await this.uploadImage();
        }

        const updatedUser = {
          usuario_id: this.profile.perfil_id,
          nombre: this.profile.nombre,
          apellido: this.profile.apellido,
          correo: this.profile.correo,
          img: imageUrl
        };

        await axios.put(`https://tnbzjbceklpojzikytcj.supabase.co/rest/v1/usuarios?usuario_id=eq.${this.profile.perfil_id}`, updatedUser, {
          headers: {
            'Content-Type': 'application/json',
            'apikey': 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRuYnpqYmNla2xwb2p6aWt5dGNqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTQ1MDc4MjMsImV4cCI6MjAzMDA4MzgyM30.j63f7-fy2z5WRSKwHHooQVJz-xM2j1XPqKek6oxhYow',
          }
        });

        toastr.success('El perfil ha sido actualizado correctamente');

        setTimeout(() => {
          this.mostrarModalEditar = false;
        }, 1000);
    
        if (this.imagen) {
          
          await this.fetch();
        }
      } catch (error) {
        console.error('Error al actualizar perfil:', error);
        toastr.error('Ocurrió un error al actualizar el perfil');
      }
    },
    onFileChange(e) {
      this.imagen = e.target.files[0];
    },
    cerrarModalEditar() {
      this.mostrarModalEditar = false;
    }
  },
  created() {
    this.fetch();
  }
};
