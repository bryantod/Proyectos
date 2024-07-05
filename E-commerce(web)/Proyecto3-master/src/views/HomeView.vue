<template>
  
  <div id="aversisale">
    <Navbar></Navbar>
    <Spinnerload></Spinnerload>
    <div id="background-wrapper">
      
      <div class="bg"></div>
      <div id="divcentro" class="wrap">
        <div id="TodoParaback">
          <div class="page-container">
            
            <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel" data-bs-interval="4000">
              <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide-to="2" aria-label="Slide 3"></button>
              </div>
              <div class="carousel-inner">
                <div class="carousel-item active">
                  <a href="/cursos">
                    <img id="carouseltama" src="@/assets/img/carousel1.jpg" alt="..."/>
                  </a>
                </div>
                <div class="carousel-item">
                  <a href="/cursos">
                    <img id="carouseltama" src="@/assets/img/carousel2.jpg" alt="..."/>
                  </a>
                </div>
                <div class="carousel-item">
                  <a href="/cursos">
                    <img id="carouseltama" src="@/assets/img/carousel3.jpg" alt="..."/>
                  </a>
                </div>
              </div>
              <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
              </button>
              <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
              </button>
            </div>
            <div id="Alineadoalcentro">
              <a href="#Hometohome" id="Botonbajar" type="button">
                <img src="@/assets/img/flecha-abajo.png" alt="..." style="width: 70px; align-items: center"/>
              </a>
            </div>
            <div id="contenedor_2">
              <div id="Hometohome" class="container-sm">
                <h1 id="textoquienes">¿Quiénes somos?</h1>
                <div id="Container-texto" class="container">
                  <h1 id="textoizquierda">Bienvenido a E-LearningEmporium!</h1>
                  <h3>&nbsp</h3>
                  <h4 id="textoizquierda">
                    En E-learningEmporium, nos dedicamos a ofrecer una amplia selección de cursos en línea diseñados para impulsar tu crecimiento personal y profesional. Con instructores expertos y contenido de calidad, estamos comprometidos a brindarte una experiencia de aprendizaje en línea excepcional. Únete a nuestra comunidad de estudiantes apasionados y comienza tu viaje de aprendizaje hoy mismo.
                  </h4>
                </div>
              </div>
              <div id="CartasDev" class="container">
                <div class="row">
                  <div id="imagenhover" class="col-lg-4 cursor-default" onclick="window.location='/Jhon'">
                    <a href="/Jhon" class="container-link">
                      <div id="textosub" class="container">
                        <img src="@/assets/img/JohnAvatar.jpeg" alt="..." style="width: 200px; border-top-left-radius: 30px; border-top-right-radius: 60px; border-bottom-left-radius: 50px; border-bottom-right-radius: 50px;"/>
                        <h2 style="color: white">Jhon</h2>
                        <img src="@/assets/img/enlace.png" style="width: 25px; position: relative; left: 84px; bottom: 37px;"/>
                        <h4 id="letraDes">Project Manager</h4>
                      </div>
                    </a>
                  </div>
                  <div id="imagenhover" class="col-lg-4" onclick="window.location='/Santiago'">
                    <a href="/Santiago" class="container-link">
                      <div id="textosub" class="container">
                        <img src="@/assets/img/SantiagoAvatar.jpg" alt="..." style="width: 200px; border-radius: 100px"/>
                        <h2 style="color: white">Santiago</h2>
                        <img src="@/assets/img/enlace.png" style="width: 25px; position: relative; left: 84px; bottom: 37px;"/>
                        <h4 id="letraDes">Web Designer</h4>
                      </div>
                    </a>
                  </div>
                  <div id="imagenhover" class="col-lg-4" onclick="window.location='/Brayan'">
                    <a href="/Brayan" class="container-link">
                      <div id="textosub" class="container">
                        <img src="@/assets/img/BryanAvatar.jpeg" alt="..." style="width: 200px; height: 200px; border-top-right-radius: 30px; border-top-left-radius: 90px; border-bottom-left-radius: 50px; border-bottom-right-radius: 50px;"/>
                        <h2 style="color: white">Brayan</h2>
                        <img src="@/assets/img/enlace.png" style="width: 25px; position: relative; left: 73px; bottom: 37px;"/>
                        <h4 id="letraDes">Back-End Developer</h4>
                      </div>
                    </a>
                  </div>
                </div>
              </div>
            </div>
            <div id="Alinear" style="text-align: right; width: 100%;">
              <a href="#" type="button" id="Alinear" style="padding-bottom: 20px; align-items: center; text-align: center;">
                <img id="Alinear" src="@/assets/img/flecha-arriba.png" alt="..." style="border-radius: 50px; outline-color: #42b983"/>
              </a>
            </div>
          </div>
        </div>
      </div>
      <FooterDefault></FooterDefault>
    </div>
  </div>
</template>

<script>
import Spinnerload from '@/components/Commons/spinnerload.vue';


export default {
  name: "HomeView",
  components: {},
};
</script>

<style scoped>
@import "@/assets/css/HomeStyle.css";
</style>
