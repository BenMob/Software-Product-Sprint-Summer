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

/*****************************************************************
 * This function requests data from the server using async await
 * @param url: The url to request from 
 * @param containerId: the id of the container in which this data is 
 *                    to be diplayed on the user interface. 
 */
 async function fetchDataAsync(url, containerId){
     const response = await fetch(url)
     const data = await response.json()
     presentData(data, containerId)
     console.log(data)
 }
 
/****************************************************************
 *This function processes responses from the server appropriatelly
 * and dpresents displays them on the page
 * @param data: an array containing the data returned from the
 *                 server 
 *********/
function presentData(data, containerId){
    const container = document.getElementById(containerId)
    let ul = document.createElement('ul');
    addClass(ul, 'w3-ul', 'w3-round-16') // Adds some classes to the ul for styling
    data.forEach(dataItem => {
        ul.appendChild(createHtmlTag('li', dataItem))     
    })
    container.appendChild(ul)
}

/***************************************************************
 *This function creates and html tag and populates it with text
 * @param tagName: Any html tag name 
 * @param text: text content you want to see in the tag
 *********/
 function createHtmlTag(tagName, text){
     const tag = document.createElement(tagName)
     const content = document.createTextNode(text)
     tag.appendChild(content)
     addClass(tag, 'w3-border-black') // Adds a class to the li for styling
     return tag
 }

/***************************************************************
 * This funtion adds a classes insite 
 * @param htmlTag: Any HTML tag or element 
 * @param ...classes: list of classes you wnat to add
 *********/
function addClass(htmlTag, ...cssClasses){
    cssClasses.forEach((cssClass) => {
        htmlTag.classList.add(cssClass)
    })
    return htmlTag
}