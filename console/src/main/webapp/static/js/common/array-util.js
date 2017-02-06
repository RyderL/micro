Array.prototype.removeObj = function(obj)
{
    var numDeleteIndex = -1;
    for (var i=0; i<this.length; i++)
    {
        // 严格比较，即类型与数值必须同时相等。
        if (this[i] === obj)
        {
            this.splice(i, 1);
            numDeleteIndex = i;
            break;
        }
    }
    return numDeleteIndex;
}