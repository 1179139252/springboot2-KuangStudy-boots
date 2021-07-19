
local limitCount = redis.call('incr',KEYS[1]);

if limitCount == 1 then
    redis.call("expire",KEYS[1],ARGV[1])
    return 1
end

if limitCount > tonumber(ARGV[2]) then
    return 0
end
