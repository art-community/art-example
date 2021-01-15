#!/usr/bin/env tarantool

require('strict').on()

local fiber = require('fiber')

-- Check if we are running under test-run
if os.getenv('ADMIN') then
    test_run = require('test_run').new()
    require('console').listen(os.getenv('ADMIN'))
end




--_________________________________________configuring mapping replication______________________________________________
-- Get instance name
local fio = require('fio')
local NAME = fio.basename(arg[0], '.lua')

--calling index config provider
mappingcfg = require('mappingcfg')
box.cfg(mappingcfg[NAME])


--___________________________________________configuring vshard router__________________________________________________
replicasets = {'cbf06940-0790-498b-948d-042b62cf3d29',
    'ac522f65-aa94-4134-9f64-51ee384f1a54'}


-- Call a configuration provider
cfg = require('localcfg')
if arg[1] == 'discovery_disable' then
    cfg.discovery_mode = 'off'
end

-- Start the database with sharding

vshard = require('vshard')
vshard.router.cfg(cfg)
require('art/router')

--_________________________________________________adding user__________________________________________________________
box.once("username:password", function()
    box.schema.user.create('username', {password = 'password', if_not_exists = true})
    box.schema.user.grant('username', 'read,write,execute,create,alter,drop', 'universe', nil, {if_not_exists=true})
    print('username:password executed on router')
end)

box.once('bootstrap', function()
    vshard.router.bootstrap()
end)
