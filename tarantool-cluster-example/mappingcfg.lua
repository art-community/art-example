return {
    ['router_1'] = {
        listen      = 3311,
        replication =   {'username:password@127.0.0.1:3311',
            'username:password@127.0.0.1:3312'
            },
        read_only   = false
    },
    ['router_2'] = {
        listen      = 3312,
        replication =   {'username:password@127.0.0.1:3311',  -- URI мастера 1
            'username:password@127.0.0.1:3312'}, -- URI мастера 2
        read_only   = false
    }
}
