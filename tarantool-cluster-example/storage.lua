#!/usr/bin/env tarantool

require('strict').on()

-- Get instance name
local fio = require('fio')
local NAME = fio.basename(arg[0], '.lua')
local fiber = require('fiber')

-- Check if we are running under test-run
if os.getenv('ADMIN') then
    test_run = require('test_run').new()
    require('console').listen(os.getenv('ADMIN'))
end

-- Call a configuration provider
cfg = require('localcfg')
-- Name to uuid map
names = {
    ['storage_1_a'] = '8a274925-a26d-47fc-9e1b-af88ce939412',
    ['storage_1_b'] = '3de2e3e1-9ebe-4d0d-abb1-26d301b84633',
    ['storage_2_a'] = '1e02ae8a-afc0-4e91-ba34-843a356b8ed7',
    ['storage_2_b'] = '001688c3-66f8-4a31-8e19-036c17d489c2',
}

replicasets = {'cbf06940-0790-498b-948d-042b62cf3d29',
    'ac522f65-aa94-4134-9f64-51ee384f1a54' }



-- Start the database with sharding
vshard = require('vshard')
vshard.storage.cfg(cfg, names[NAME])

--init art module
require('art/storage')

--mapping init(only for vshard)
--map replicasets uuid to vshard mapping uri
mapping_uri = {
    ['cbf06940-0790-498b-948d-042b62cf3d29'] = 'username:password@127.0.0.1:3311',
    ['ac522f65-aa94-4134-9f64-51ee384f1a54'] = 'username:password@127.0.0.1:3312'
}
art.cluster.mapping.init(mapping_uri)



box.once("username:password", function()
    box.schema.user.create('username', {password = 'password', if_not_exists = true})
    box.schema.user.grant('username', 'read,write,execute,create,alter,drop', 'universe', nil, {if_not_exists=true})
    print('username:password executed on storage')
end)
