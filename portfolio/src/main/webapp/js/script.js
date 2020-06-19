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

 async function fetchLogout(){
     const response = await fetch("/authentication", {
         method:'POST',
         headers: {'Content-Type': 'text/plain'},
         body: JSON.stringify({'logout':'true'})
        })
 }
 
/****************************************************************
 * This function processes responses from the server appropriately
 * and dpresents displays them on the page
 * @param data: an array containing the data returned from the
 *                 server 
 *********/
function presentData(data, containerId){
    const container = document.getElementById(containerId)
    let ul = document.createElement('ul');
    addClass(ul, 'w3-ul', 'w3-border-0', 'w3-round-16')
    data.forEach(dataItem => {
        let author = createHtmlTag('p', '').appendChild(createHtmlTag('strong', dataItem.author))
        let comment = createHtmlTag('p', dataItem.comment)
        addClass(comment, 'w3-padding', 'w3-round-xxlarge', 'yellow-bg', 'blue1-text', 'smaller-text')

        let commentBlock = createHtmlTag('li', '')
        addClass(commentBlock, 'w3-panel', 'black-shadow', "w3-round-xlarge", 'tiny-bottom-margin', 'w3-padding')

        commentBlock.appendChild(author)
        commentBlock.appendChild(comment)

        
        ul.appendChild(commentBlock)    
    })
    container.appendChild(ul)
}

/***************************************************************
 * This function creates and html tag and populates it with text
 * @param tagName: Any html tag name 
 * @param text: text content you want to see in the tag
 *********/
 function createHtmlTag(tagName, text){
     const tag = document.createElement(tagName)
     const content = document.createTextNode(text)
     tag.appendChild(content)
     return tag
 }

/***************************************************************
 * This funtion adds a classes insite 
 * @param htmlTag: Any HTML tag or element 
 * @param ...classes: list of classes you want to add
 *********/
function addClass(htmlTag, ...cssClasses){
    cssClasses.forEach((cssClass) => {
        htmlTag.classList.add(cssClass)
    })
    return htmlTag
}