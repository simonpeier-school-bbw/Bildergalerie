function openPhotoSwipe() {
    const pswpElement = document.querySelectorAll('.pswp')[0];

    let items = [
        {
            src: '../img/3_cats.jpg',
            w: 600,
            h: 400
        },
        {
            src: '../img/3_cats_2.jpg',
            w: 600,
            h: 400
        },
        {
            src: '../img/cat.jpg',
            w: 600,
            h: 400
        }
    ];

    const options = {
        index: 0 // start at first slide
    };

    const gallery = new PhotoSwipe(pswpElement, PhotoSwipeUI_Default, items, options);
    gallery.init();
}

function getImagesFromFolder() {
    let images = [];
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "/img", true);
    xhr.responseType = 'document';
    xhr.onload = () => {
        if (xhr.status === 200) {
            const elements = xhr.response.getElementsByTagName("a");
            for (let x of elements) {
                if (x.href.match(/\.(jpe?g|png|gif)$/)) {
                    images.push(x.href);
                }
            }
        } else {
            console.log('Request failed. Returned status of ' + xhr.status);
        }
    };
    xhr.send();
    images.forEach(item => console.log(item));
    return images;
}
document.getElementById('openGallery').onclick = openPhotoSwipe;