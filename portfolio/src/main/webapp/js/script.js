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
 