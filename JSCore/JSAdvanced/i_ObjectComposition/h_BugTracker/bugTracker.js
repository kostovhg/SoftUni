function result(){

    let id = 0;
    let allReports = new Map();
    let element = null;
    let module = {
        report: (author, description, isReproducible, severity) => {
            allReports.set(id++, {
                author,
                description,
                isReproducible,
                severity,
                status: 'Open'
            });
            module.output(element);
        },
        setStatus: (id, newStatus) => {
            allReports.get(id).status = newStatus;
            module.output(element);
        },
        remove: (id) => {
            allReports.delete(id);
            module.output(element);
        },
        sort: (criteria) => {
           allReports = [...allReports].sort((a, b) => {
                return (criteria === 'ID' || !criteria) ?
                    a[0] - b[0] : (criteria === 'severity') ?
                        a[1].severity - b[1].severity :
                        a[1][criteria].localeCompare(b[1][criteria]);
            });
            module.output(element);
        },
        output: (selector) => {
            element = $(selector);
            $(selector).empty();
            for (let [id, report] of [...allReports]) {
                let reportDiv = $('<div>')
                        .attr('id', `report_${id}`)
                        .addClass('report')
                        .append($('<body>')
                            .addClass('body')
                            .append($(`<p>${report.description}</p>`)))
                        .append($('<div>')
                            .addClass('title')
                            .append($(`<span>`)
                                .addClass('author')
                                .text(`Submitted: ${report.author}`))
                            .append($(`<span>`)
                                .addClass('status')
                                .text(`${report.status} | ${report.severity}`))
                        );
                $(selector).append(reportDiv);
            }
        }
    };
    return module;
}


let tracker = result();

tracker.output('#content');
tracker.report('guy', 'report content', true, 5);
tracker.report('second guy', 'report content 2', true, 3);
tracker.report('abv', 'report content three', true, 4);

tracker.sort('author');