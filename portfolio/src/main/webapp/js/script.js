// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/********************************************************
 * Listens to the about link in the navigation bar and
 * turns the text to yellow when the about section is visited.  
 *
document.getElementById('about-link').addEventListener('click', () => {
    let about = document.getElementById('about-link')
    let contact = document.getElementById('contact-link')
    let projects = document.getElementById('projects-link')

    if(!about.classList.contains('yellow-text'))
  	    about.classList.add('yellow-text')

    if(contact.classList.contains('yellow-text'))
  	    contact.classList.remove('yellow-text')

    if(projects.classList.contains('yellow-text'))
        projects.classList.remove('yellow-text') 
})

/********************************************************
 * Listens to the projects link in the navigation bar and
 * turns the text to yellow when the projects section is visited.  
 *
document.getElementById('projects-link').addEventListener('click', () => {
    let about = document.getElementById('about-link')
    let contact = document.getElementById('contact-link')
    let projects = document.getElementById('projects-link')

    if(!projects.classList.contains('yellow-text'))
        projects.classList.add('yellow-text')

    if(contact.classList.contains('yellow-text'))
        contact.classList.remove('yellow-text')

    if(about.classList.contains('yellow-text'))
        about.classList.remove('yellow-text')
})

/********************************************************
 * Listens to the contact link in the navigation bar and
 * turns the text to yellow when the contact section is visited.  
 *
document.getElementById('contact-link').addEventListener('click', () => {
    let about = document.getElementById('about-link')
    let contact = document.getElementById('contact-link')
    let projects = document.getElementById('projects-link')

    if(!contact.classList.contains('yellow-text'))
        contact.classList.add('yellow-text')

    if(about.classList.contains('yellow-text'))
        about.classList.remove('yellow-text')

    if(projects.classList.contains('yellow-text'))
        projects.classList.remove('yellow-text')
})*/
/**
    Fetch demo
 */
 function fetchData(){
     fetch('/data')
     .then((response) => response.text()
     .then((text) => showData(text)), () => console.log("Failed to get informatin at /data"))
 }

 function showData(data){
     let container = document.getElementById('data-container').classList
     let content = document.getElementById('data')

     container.add('w3-container', 'w3-border', 'w3-white')
     content.innerText = data
     
 }
 