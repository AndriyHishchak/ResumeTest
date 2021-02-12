window.addEventListener('load', () => {
    const setActiveLink = () => {
        const prevLink = document.querySelector('a.active');
        let currentLink = document.querySelector(`a[href="${location.hash}"]`);
        if(prevLink) {
            prevLink.classList.remove('active');
        }
        if (!currentLink) {
           currentLink = document.querySelector('a[href="#main"]');
        }
        currentLink.classList.add('active');
    }
    const init = () => {
        const url = '/edit/main';
        fetch(url).then(response => {
            return response.text();
        }).then(content => {
            const contentDiv = document.querySelector('#content');
            contentDiv.innerHTML = content;
        })
    }
    window.addEventListener('hashchange', setActiveLink);
    init();
    setActiveLink();
});

const loadPage = (link) => {
    const pageUrl = link.getAttribute("href").replace('#', '');
    const url = `/edit/${pageUrl}`;
    fetch(url).then(response => {
        return response.text();
    }).then(content => {
        const contentDiv = document.querySelector('#content');
        contentDiv.innerHTML = content;
    });
}

const setLevel = (number) => {
    const levels = ["Beginner", "Elementary", "Pre-intermediate", "Intermediate", "Upper-intermediate", "Advanced", "Proficiency"];
    const tooltip = document.querySelector("#levelTooltip");
    tooltip.innerHTML = levels[number];
}

const activeCheckboxes = [];

const check = (checkbox) => {
    let arr = [...checkbox.classList];
    if (arr.includes('active')) {
        activeCheckboxes.push(checkbox);
        if (activeCheckboxes.length > 5) {
            activeCheckboxes[0].classList.remove('active');
            activeCheckboxes.splice(0, 1);
        }
    } else {
        let index = activeCheckboxes.findIndex(elem => checkbox === elem);
        activeCheckboxes.splice(index, 1);
    }
    console.log(activeCheckboxes);
}