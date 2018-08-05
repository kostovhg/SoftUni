let catalogController = (() => {
    function loadCatalog(ctx) {

        teamsService.loadTeams().then((res) => {
            loadCommon(this, {team: './templates/catalog/team.hbs'}, {
                teams: res.map(e => {
                    return {_id: e._id, name: e.name, content: e.description}
                }), hasNoTeam: res.length < 1
            })
                .then(function (res) {
                    this.partial('./templates/catalog/teamCatalog.hbs', ctx.params);
                })
        });
    }

    function loadCreate(ctx) {
        loadCommon(this, {createForm: './templates/create/createForm.hbs'})
            .then(function (res) {
                this.partial('./templates/create/createPage.hbs');
                console.log(res);
            });
    }

    function sendCreate(ctx) {
        let [teamName, teamComment] = [ctx.params.name, ctx.params.comment];
        teamsService.createTeam(teamName, teamComment)
            .then((res) => {
                console.log('You have successfully created team ' + teamName + '.');
                console.log(res);
                teamsService.joinTeam(res._id).then(function (responce) {
                    ctx.redirect('#/catalog');
                }).catch(function (err) {
                    console.log(err);
                })

            }).catch((err) => {
            auth.showError(err.responseJSON.description);
            this.redirect('#/create');
        })
    }

    function loadDetails(ctx) {
        let _id = ctx.params._id;
        if (_id.startsWith(':')) {
            ctx.params._id = _id.substr(1);
            _id = ctx.params._id;
        }
        teamsService.loadTeamDetails(ctx.params._id)
            .then((response) => {
                let $params = response;
                $params.teamId = response._id;
                $params.isOnTeam = response._id === sessionStorage.getItem('teamId');
                $params.isAuthor = response._acl.creator === sessionStorage.getItem('userId');

                loadCommon(this,
                    {
                        teamControls: './templates/catalog/teamControls.hbs',
                        teamMember: './templates/catalog/teamMember.hbs',
                    }, $params)
                    .then(function (res) {
                        // very odd function prepare array of objects for members views in style {username: member}
                        console.log(ctx.params.members);
                        console.log(typeof ctx.params.members);
                        let members = [];
                        (ctx.params.members.split(/[\[\],\"\']/g)).forEach(m => members.push({'username': m}));
                        ctx.params.members = members;
                        this.partial('./templates/catalog/details.hbs', ctx.params);
                        console.log(res);
                    });
            }).catch((err) => console.log(err))
    }


    return {
        loadCatalog,
        loadCreate,
        sendCreate,
        loadDetails,
    }

})();
