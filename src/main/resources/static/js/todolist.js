$(document).ready(() => {
    $('#todo-button').click(() => {
        const text = $('#todo-input').val();
        const todoElement = `
        <li>
          <span class="handle">
            <i class="fas fa-ellipsis-v"></i>
            <i class="fas fa-ellipsis-v"></i>
          </span>
          <div  class="icheck-primary d-inline ml-2">
            <input type="checkbox" value="" name="todo3">
            <label for="todoCheck3"></label>
          </div>
          <span class="text">${text}</span>
          <div class="tools">
            <i class="fas fa-edit"></i>
            <i class="fas fa-trash-o"></i>
          </div>
        </li>
        `;

        $('ul.todo-list').append(todoElement);
    });

    let isComposing = false;

    $('#todo-input')
        .on('compositionstart', () => {
            isComposing = true;
        })
        .on('compositionend', () => {
            isComposing = false;
        })
        .on('keydown', (event) => {
            if (isComposing || event.keyCode === 229) return;

            if (event.key === 'Enter' || event.keyCode === 13) {
                event.preventDefault();
                $('#todo-button').click();
            }
        });

    $(document).on('dblclick','ul.todo-list li', function() {
        $(this).toggleClass('done').fadeOut('slow');
    });
});
