class Contact{
    constructor(firsName, lastName, phone, email){
        this.firstName = firsName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this._element = this.create();
        this.online = false;
    }

    set online(val){
        this._online = val;
        this.toggleStatus();
    }

    get online(){
        return this._online;
    }

    toggleStatus() {
        if(this.online) {
            $(this._element).find('.title').addClass('online');
        } else {
            $(this._element).find('.title').removeClass('online');
        }
    }

    create() {
        let article = $('<article>');
        let title = $('<div class="title">').text(`${this.firstName} ${this.lastName}`);
        let info = $('<div class="info" style="display: none">');
        info.append($('<span>').html(`&phone; ${this.phone}`));
        info.append($('<span>').html(`&#9993; ${this.email}`));
        title.append($('<button>').html('&#8505;')
            .on('click', () => info.toggle()))
        article.append(title);
        article.append(info);
        return article;
    }

    render(id){
        $(`#${id}`).append(this._element)
    }
}
