$(document).ready(function() {
    const $cards = $('.card');

    // Open and close card when clicked on card
    $cards.find('.js-expander').click(function() {
        const $thisCard = $(this).closest('.card');

        if ($thisCard.hasClass('is-collapsed')) {
            $cards.not($thisCard).removeClass('is-expanded').addClass('is-collapsed').addClass('is-inactive');
            $thisCard.removeClass('is-collapsed').addClass('is-expanded');

            if ($cards.not($thisCard).hasClass('is-inactive')) {
                // Do nothing
            } else {
                $cards.not($thisCard).addClass('is-inactive');
            }
        } else {
            $thisCard.removeClass('is-expanded').addClass('is-collapsed');
            $cards.not($thisCard).removeClass('is-inactive');
        }
    });

    // Close card when clicking on cross
    $cards.find('.js-collapser').click(function() {
        const $thisCard = $(this).closest('.card');

        $thisCard.removeClass('is-expanded').addClass('is-collapsed');
        $cards.not($thisCard).removeClass('is-inactive');
    });
});

// 추천 게시물용

window.addEventListener("DOMContentLoaded", () => {
    const nc = new NotificationCenter();
});

class NotificationCenter {
    constructor() {
        this.items = [];
        this.itemsToKill = [];
        this.messages = NotificationMessages();
        this.killTimeout = null;

        this.spawnNotes(3);
    }

    spawnNote() {
        const id = this.random(0, 2 ** 32, true).toString(16);  
        const draw = this.random(0, this.messages.length - 1, true);
        const message = this.messages[draw];
        const note = new Notification({
            id: `note-${id}`,
            icon: message.icon,
            title: message.title,
            subtitle: message.subtitle,
            actions: message.actions,
        });
        const transY = 10 * this.items.length;

        note.el.style.transform = `translateY(${transY}%)`; 
        note.el.addEventListener("click", (e) => this.killNote(note.id, e));

        this.items.push(note);
    }

    spawnNotes(amount) {
        let count = typeof amount === "number" ? amount : this.random(1, 5, true);

        while (count--) this.spawnNote();
    }

    killNote(id, e) {
        const note = this.items.find((item) => item.id === id);
        const tar = e.target;

        if (note && tar.getAttribute("data-dismiss") === id) {
            note.el.classList.add("notification--out");
            this.itemsToKill.push(note);

            clearTimeout(this.killTimeout);

            this.killTimeout = setTimeout(() => {
                this.itemsToKill.forEach((itemToKill) => {
                    const parent = document.querySelector('.notification');
                    parent.removeChild(itemToKill.el);

                    const left = this.items.filter((item) => item.id !== itemToKill.id);
                    this.items = [...left];
                });

                this.itemsToKill = [];

                if (!this.items.length) this.spawnNotes();
                else this.spawnNotes(this.random(0, 1, true));

                this.shiftNotes();
            }, note.killTime);
        }
    }

    shiftNotes() {
        this.items.forEach((item, i) => {
            const transY = 10 * i;
            item.el.style.transform = `translateY(${transY}%)`; 
        });
    }

    random(min, max, round = false) {
        const percent = crypto.getRandomValues(new Uint32Array(1))[0] / 2 ** 32;
        const relativeValue = (max - min) * percent;

        return min + (round === true ? Math.round(relativeValue) : +relativeValue.toFixed(2));
    }
}

class Notification {
    constructor(args) {
        this.args = args;
        this.el = null;
        this.id = null;
        this.killTime = 300;
        this.init(args);
    }

    init(args) {
        const { id, title, subtitle, actions } = args;
        const block = "notification";
        const parent = document.querySelector('.notification');

        const note = this.newEl("div");
        note.id = id;
        note.className = block;
        parent.appendChild(note);

        const box = this.newEl("div");
        box.className = `${block}__box`;
        note.appendChild(box);

        const content = this.newEl("div");
        content.className = `${block}__content`;
        box.appendChild(content);


        const text = this.newEl("div");
        text.className = `${block}__text`;
        content.appendChild(text);

        const _title = this.newEl("div");
        _title.className = `${block}__text-title`;
        _title.textContent = title;
        text.appendChild(_title);

        if (subtitle) {
            const _subtitle = this.newEl("div");
            _subtitle.className = `${block}__text-subtitle`;
            _subtitle.textContent = subtitle;
            text.appendChild(_subtitle);
        }

        const btns = this.newEl("div");
        btns.className = `${block}__btns`;
        box.appendChild(btns);

        actions.forEach((action) => {
            const btn = this.newEl("button");
            btn.className = `${block}__btn`;
            btn.type = "button";
            btn.setAttribute("data-dismiss", id);

            const btnText = this.newEl("span");
            btnText.className = `${block}__btn-text`;
            btnText.textContent = action;

            btn.appendChild(btnText);
            btns.appendChild(btn);
        });

        this.el = note;
        this.id = note.id;
    }

    newEl(elName, NSValue) {
        if (NSValue) return document.createElementNS(NSValue, elName);
        else return document.createElement(elName);
    }
}

function NotificationMessages() {
    return [
        {
            title: "글 제목 1",
			subtitle: "작성자 1",
            actions: ["아 딴거"]
        },
        {
            title: "글 제목 2",
            subtitle: "작성자 2",
            actions: ["아 딴거"]
        },
        {
            title: "글 제목 3",
            subtitle: "작성자 3",
            actions: ["아 딴거"]
        },
    ];
}
